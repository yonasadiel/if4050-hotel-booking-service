from __future__ import unicode_literals

from django.db import models
import requests


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

    def update_room(self):
        r = requests.get('http://localhost:8004/api/room/type/%d/' % (self.room_type))
        res = r.json()
        self.room_number = res[0].get('room_number')
        self.price = res[0].get('type_room').get('price')

    def save(self):
        self.update_room()
        super(Booking, self).save()
