from rest_framework import serializers
from hotelbooking_room.room.models import Room, RoomType


class RoomTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = RoomType
        fields = ('name', 'price')
        read_only_fields = ('name', 'price')


class RoomSerializer(serializers.ModelSerializer):
    type_room = RoomTypeSerializer()

    class Meta:
        model = Room
        fields = ('room_number', 'type_room')
        read_only_fields = ('room_number', 'type_room')
