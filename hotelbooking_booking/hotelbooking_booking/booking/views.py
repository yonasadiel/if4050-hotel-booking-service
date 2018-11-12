from hotelbooking_booking.booking.models import Booking
from hotelbooking_booking.booking.serializers import BookingSerializer
from rest_framework import views, generics
from rest_framework.response import Response


class ListBookingView(views.APIView):

    def post(self, request):
        request_serializer = BookingSerializer(data=request.data)
        request_serializer.is_valid(raise_exception=True)

        booking = Booking()
        booking.check_in = request_serializer.validated_data.get('check_in', None)
        booking.check_out = request_serializer.validated_data.get('check_out', None)
        booking.payment_name = request_serializer.validated_data.get('payment_name', None)
        booking.payment_type = request_serializer.validated_data.get('payment_type', None)
        booking.room_type = request_serializer.validated_data.get('room_type', None)
        booking.guest_id = request_serializer.validated_data.get('guest_id', None)
        booking.save()

        return Response(BookingSerializer(booking).data)


class DetailBookingView(generics.RetrieveUpdateAPIView):
    serializer_class = BookingSerializer
    queryset = Booking.objects.all()


class BookingByGuestView(views.APIView):

    def get(self, request, guest_id):
        bookings = Booking.objects.filter(guest_id=guest_id)
        return Response(BookingSerializer(bookings, many=True).data)
