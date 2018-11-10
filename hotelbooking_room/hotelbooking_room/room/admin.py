from django.contrib import admin
from hotelbooking_room.room.models import Room, RoomType


@admin.register(Room)
class RoomAdmin(admin.ModelAdmin):
    list_display = ['room_number', 'type_room']
    search_fields = ['room_number', 'type_room']


@admin.register(RoomType)
class RoomTypeAdmin(admin.ModelAdmin):
    list_display = ['name', 'price']
    search_fields = ['name', 'price']
