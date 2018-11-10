from hotelbooking_guest.guest.models import Guest
from hotelbooking_guest.guest.serializers import GuestSerializer
from rest_framework import generics


class ListGuestView(generics.ListCreateAPIView):
    queryset = Guest.objects.all()
    serializer_class = GuestSerializer


class DetailGuestView(generics.RetrieveUpdateAPIView):
    queryset = Guest.objects.all()
    serializer_class = GuestSerializer
