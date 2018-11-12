from rest_framework import serializers
from hotelbooking_booking.booking.models import Booking


class BookingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Booking
        fields = (
            'id', 'check_in', 'check_out', 'payment_name', 'payment_type',
            'room_type', 'guest_id', 'room_number', 'price', 'status')
        read_only_fields = ('id', 'room_number', 'price')
