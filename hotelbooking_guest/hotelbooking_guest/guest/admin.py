from django.contrib import admin
from hotelbooking_guest.guest.models import Guest


@admin.register(Guest)
class BookingAdmin(admin.ModelAdmin):
    list_display = ['name', 'email', 'identity_number', 'mobile_phone']
    search_fields = ['name', 'email']
