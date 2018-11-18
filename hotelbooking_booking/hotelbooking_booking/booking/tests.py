from datetime import date, datetime, timedelta, timezone
from django.urls import reverse
from hotelbooking_booking.booking.models import Booking
from unittest.mock import patch
from rest_framework import status
from rest_framework.test import APITestCase


class BookingTests(APITestCase):

    def test_create_booking(self):
        url = reverse('booking-list')
        data = {
            'check_in': '2018-10-2',
            'check_out': '2018-10-12',
            'payment_name': 'Yonas',
            'payment_type': 'BNI',
            'room_type': '1',
            'guest_id': '1',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)
        self.assertEqual(Booking.objects.count(), 1)

        booking = Booking.objects.get()
        self.assertEqual(booking.payment_name, 'Yonas')
        self.assertEqual(booking.payment_type, 'BNI')
        self.assertEqual(booking.room_type, 1)
        self.assertEqual(booking.guest_id, 1)
        self.assertEqual(booking.room_number, 'abc')
        self.assertEqual(booking.price, 1234)
        self.assertEqual(booking.check_in, date(2018, 10, 2))
        self.assertEqual(booking.check_out, date(2018, 10, 12))

    def test_create_booking_wrong_date(self):
        url = reverse('booking-list')
        data = {
            'check_in': '2018-10-22',
            'check_out': '2018-10-12',
            'payment_name': 'Yonas',
            'payment_type': 'BNI',
            'room_type': '1',
            'guest_id': '1',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.post(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
        self.assertEqual(response.data.get('code'), 'check_in_check_out_date_failed')
        self.assertEqual(Booking.objects.count(), 0)

    def test_get_booking(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_get_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.save()

        url = reverse('booking-detail', args=[booking.id])
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data.get('payment_name'), booking.payment_name)

    def test_get_booking_failed(self):
        url = reverse('booking-detail', args=[1])
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)

    def test_put_bookng(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_update_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.status = 'PENDING'
        booking.save()
        booking.date_created = datetime.now(timezone.utc)
        booking.save()

        url = reverse('booking-detail', args=[1])
        data = {
            'check_in': date(2018, 10, 2),
            'check_out': date(2018, 10, 22),
            'payment_name': 'test_update_booking_updated',
            'payment_type': 'new_type',
            'room_type': '2',
            'guest_id': '3',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(Booking.objects.count(), 1)

        booking = Booking.objects.get()
        self.assertEqual(booking.payment_name, data.get('payment_name'))
        self.assertEqual(booking.payment_type, data.get('payment_type'))
        self.assertEqual(booking.room_type, int(data.get('room_type')))
        self.assertEqual(booking.guest_id, int(data.get('guest_id')))
        self.assertEqual(booking.room_number, room_mock.get('room_number'))
        self.assertEqual(booking.price, room_mock.get('type_room').get('price'))
        self.assertEqual(booking.check_in, data.get('check_in'))
        self.assertEqual(booking.check_out, data.get('check_out'))

    def test_put_bookng_empty_field(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_update_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.status = 'PENDING'
        booking.save()
        booking.date_created = datetime.now(timezone.utc) - timedelta(minutes=55)
        booking.save()

        url = reverse('booking-detail', args=[1])
        data = {
            'payment_name': 'test_update_booking_updated_empty_other',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(Booking.objects.count(), 1)

        booking = Booking.objects.get()
        self.assertEqual(booking.payment_name, data.get('payment_name'))
        self.assertEqual(booking.payment_type, booking.payment_type)
        self.assertEqual(booking.room_type, booking.room_type)
        self.assertEqual(booking.guest_id, booking.guest_id)
        self.assertEqual(booking.room_number, room_mock.get('room_number'))
        self.assertEqual(booking.price, room_mock.get('type_room').get('price'))
        self.assertEqual(booking.check_in, booking.check_in)
        self.assertEqual(booking.check_out, booking.check_out)

    def test_put_expired_bookng(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_update_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.status = 'PENDING'
        booking.save()
        booking.date_created = datetime.now(timezone.utc) - timedelta(hours=1, minutes=2)
        booking.save()

        url = reverse('booking-detail', args=[1])
        data = {
            'check_in': date(2018, 10, 2),
            'check_out': date(2018, 10, 22),
            'payment_name': 'test_update_booking_updated',
            'payment_type': 'new_type',
            'room_type': '2',
            'guest_id': '3',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
        self.assertEqual(response.data.get('code'), 'booking_has_expired')

    def test_put_paid_bookng(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_update_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.status = 'PAID'
        booking.save()
        booking.date_created = datetime.now(timezone.utc) - timedelta(hours=1, minutes=2)
        booking.save()

        url = reverse('booking-detail', args=[1])
        data = {
            'check_in': date(2018, 10, 2),
            'check_out': date(2018, 10, 22),
            'payment_name': 'test_update_booking_updated',
            'payment_type': 'new_type',
            'room_type': '2',
            'guest_id': '3',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
        self.assertEqual(response.data.get('code'), 'booking_is_not_pending')

    def test_put_cancelled_bookng(self):
        booking = Booking()
        booking.check_in = date(2018, 10, 2)
        booking.check_out = date(2018, 10, 3)
        booking.payment_name = 'test_update_booking'
        booking.payment_type = 'BNI'
        booking.room_type = 1
        booking.guest_id = '1'
        booking.room_number = '123'
        booking.price = 20000
        booking.status = 'CANCELLED'
        booking.save()
        booking.date_created = datetime.now(timezone.utc) - timedelta(hours=1, minutes=2)
        booking.save()

        url = reverse('booking-detail', args=[1])
        data = {
            'check_in': date(2018, 10, 2),
            'check_out': date(2018, 10, 22),
            'payment_name': 'test_update_booking_updated',
            'payment_type': 'new_type',
            'room_type': '2',
            'guest_id': '3',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_400_BAD_REQUEST)
        self.assertEqual(response.data.get('code'), 'booking_is_not_pending')

    def test_put_not_found_bookng(self):
        url = reverse('booking-detail', args=[1])
        data = {
            'check_in': date(2018, 10, 2),
            'check_out': date(2018, 10, 22),
            'payment_name': 'test_update_booking_updated',
            'payment_type': 'new_type',
            'room_type': '2',
            'guest_id': '3',
        }
        room_mock = {
            'room_number': 'abc',
            'type_room': {
                'price': 1234,
            }
        }
        with patch.object(Booking, 'get_available_room', return_value=room_mock):
            response = self.client.put(url, data, format='json')
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)
        self.assertEqual(response.data.get('code'), 'booking_is_not_found')
