from django.contrib import admin
from hotelbooking_booking.booking.models import Booking


@admin.register(Booking)
class BookingAdmin(admin.ModelAdmin):
    list_display = ['room_number', 'check_in', 'check_out']
    search_fields = ['room_number', 'payment_name']
