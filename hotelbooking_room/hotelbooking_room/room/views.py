from hotelbooking_room.room.models import Room
from hotelbooking_room.room.serializers import RoomSerializer
from rest_framework import generics


class RoomView(generics.RetrieveAPIView):
    lookup_field = 'room_number'
    queryset = Room.objects.all()
    serializer_class = RoomSerializer
