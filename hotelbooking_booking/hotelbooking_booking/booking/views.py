from hotelbooking_booking.booking.models import Booking
from hotelbooking_booking.booking.serializers import BookingSerializer
from rest_framework import views, status
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
        if (booking.check_in >= booking.check_out):
            return Response(dict(
                code='check_in_check_out_date_failed',
                detail='Booking check in date should at least 1 day before check out date',
            ), status=status.HTTP_400_BAD_REQUEST)
        booking.update_room()
        booking.save()

        return Response(BookingSerializer(booking).data, status=status.HTTP_201_CREATED)


class DetailBookingView(views.APIView):
    serializer_class = BookingSerializer
    queryset = Booking.objects.all()

    def get(self, request, booking_id):
        try:
            booking = Booking.objects.get(id=booking_id)
            return Response(BookingSerializer(booking).data)
        except Booking.DoesNotExist:
            return Response(dict(
                code='booking_is_not_found',
                detail='No booking with id %d' % (booking_id),
            ), status=status.HTTP_404_NOT_FOUND)

    def put(self, request, booking_id):
        try:
            booking = Booking.objects.get(id=booking_id)
            if (booking.is_pending):
                if (not booking.expired):
                    request_serializer = BookingSerializer(data=request.data, partial=True)
                    request_serializer.is_valid(raise_exception=True)

                    new_booking_status = request_serializer.validated_data.get('status', booking.status)
                    if (new_booking_status != 'CANCELLED'):
                        new_booking_status = booking.status

                    booking.check_in = request_serializer.validated_data.get('check_in', booking.check_in)
                    booking.check_out = request_serializer.validated_data.get('check_out', booking.check_out)
                    booking.payment_name = request_serializer.validated_data.get('payment_name', booking.payment_name)
                    booking.payment_type = request_serializer.validated_data.get('payment_type', booking.payment_type)
                    booking.room_type = request_serializer.validated_data.get('room_type', booking.room_type)
                    booking.guest_id = request_serializer.validated_data.get('guest_id', booking.guest_id)
                    booking.status = new_booking_status
                    booking.update_room()
                    booking.save()
                    return Response(BookingSerializer(booking).data)

                return Response(dict(
                    code='booking_has_expired',
                    detail='Booking has expired',
                ), status=status.HTTP_400_BAD_REQUEST)

            return Response(dict(
                code='booking_is_not_pending',
                detail='Booking is not pending',
            ), status=status.HTTP_400_BAD_REQUEST)

        except Booking.DoesNotExist:
            return Response(dict(
                code='booking_is_not_found',
                detail='No booking with id %d' % (booking_id),
            ), status=status.HTTP_404_NOT_FOUND)


class BookingByGuestView(views.APIView):

    def get(self, request, guest_id):
        bookings = Booking.objects.filter(guest_id=guest_id)
        return Response(BookingSerializer(bookings, many=True).data)


class BookingStatus(views.APIView):

    def post(self, request, booking_id, booking_status):
        try:
            booking = Booking.objects.get(id=booking_id)
            if (booking.is_pending):
                if (not booking.expired):
                    booking.status = booking_status
                    booking.save()
                    return Response(BookingSerializer(booking).data)

                return Response(dict(
                    code='booking_has_expired',
                    detail='Booking has expired',
                ), status=status.HTTP_400_BAD_REQUEST)

            return Response(dict(
                code='booking_is_not_pending',
                detail='Booking is not pending',
            ), status=status.HTTP_400_BAD_REQUEST)

        except Booking.DoesNotExist:
            return Response(dict(
                code='booking_is_not_found',
                detail='No booking with id %d' % (booking_id),
            ), status=status.HTTP_404_NOT_FOUND)
