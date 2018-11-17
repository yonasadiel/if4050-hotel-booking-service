from hotelbooking_room.room.models import Room, RoomType
from hotelbooking_room.room.serializers import RoomSerializer, RoomTypeSerializer
from rest_framework import generics, views
from rest_framework.response import Response


class RoomView(generics.RetrieveAPIView):
    lookup_field = 'room_number'
    queryset = Room.objects.all()
    serializer_class = RoomSerializer


class RoomTypeView(generics.ListAPIView):
    queryset = RoomType.objects.all()
    serializer_class = RoomTypeSerializer


class RoomByTypeView(views.APIView):

    def get(self, request, type_room):
        type_room = RoomType.objects.filter(pk=type_room).first()
        rooms = Room.objects.filter(type_room=type_room)
        return Response(RoomSerializer(rooms, many=True).data)
