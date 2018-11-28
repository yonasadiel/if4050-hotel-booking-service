from __future__ import unicode_literals
from datetime import datetime, timedelta, timezone
from django.db import models
import requests


def intersect(booking1, booking2):
    if (booking1.check_in <= booking2.check_in and booking2.check_in < booking1.check_out):
        return True
    if (booking1.check_in < booking2.check_out and booking2.check_out <= booking1.check_out):
        return True
    if (booking2.check_in <= booking1.check_in and booking1.check_in < booking2.check_out):
        return True
    if (booking2.check_in < booking1.check_out and booking1.check_out <= booking2.check_out):
        return True
    return False


class Booking(models.Model):
    BOOKING_STATUS_CHOICE = (
        ('PENDING', 'PENDING'),
        ('PAID', 'PAID'),
        ('CANCELLED', 'CANCELLED')
    )

    check_in = models.DateField()
    check_out = models.DateField()
    payment_name = models.CharField(max_length=100, null=True, blank=True)
    payment_type = models.CharField(max_length=20, null=True, blank=True)
    room_number = models.CharField(max_length=3, null=True)
    room_type = models.IntegerField(null=True)
    price = models.DecimalField(max_digits=14, decimal_places=2)
    guest_id = models.IntegerField(null=True)
    status = models.CharField(max_length=10, default="PENDING", choices=BOOKING_STATUS_CHOICE)
    date_created = models.DateTimeField(auto_now_add=True, editable=True)
    payment_id = models.IntegerField(null=True)

    def __str__(self):
        return 'Booking %d' % (self.id)

    def update_room(self):
        room = self.get_available_room()
        self.room_number = room.get('room_number')
        self.price = room.get('type_room').get('price')

    def get_available_room(self):
        r = requests.get('http://localhost:8004/api/room/type/%d/' % (self.room_type))
        rooms = r.json()
        available_rooms = []
        for room in rooms:
            bookings_with_same_room = Booking.objects.filter(room_number=room.get('room_number'))
            possible = True
            one_hour_before = datetime.now(timezone.utc) - timedelta(hours=1)
            for booking_with_same_room in bookings_with_same_room:
                if (intersect(self, booking_with_same_room)):
                    if (booking_with_same_room.date_created >= one_hour_before):
                        possible = False
                    if (booking_with_same_room.status == 'PAID'):
                        possible = False
            if (possible):
                available_rooms.append(room)
        return available_rooms[0]

    @property
    def expired(self):
        last_hour = datetime.now(timezone.utc) - timedelta(hours=1)
        return self.date_created < last_hour

    @property
    def is_pending(self):
        return self.status == 'PENDING'
