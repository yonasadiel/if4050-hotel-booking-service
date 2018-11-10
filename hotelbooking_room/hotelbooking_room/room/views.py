from hotelbooking_room.room.models import Room
from rest_framework import views, status
from rest_framework.response import Response


class RoomView(views.APIView):

    def get(self, request, room_number):
        room = Room.objects.filter(room_number=room_number).first()
        if room is None:
            return Response({}, status=status.HTTP_404_NOT_FOUND)
        return Response(room)
