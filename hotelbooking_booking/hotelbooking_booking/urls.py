from django.urls import path
from django.contrib import admin
from hotelbooking_booking.booking.views import (
    ListBookingView, DetailBookingView,
    BookingByGuestView, BookingStatus,
)

urlpatterns = [
    path('admin/', admin.site.urls),

    path('api/booking/', ListBookingView.as_view(), name='booking-list'),
    path('api/booking/<int:booking_id>/', DetailBookingView.as_view(), name='booking-detail'),
    path('api/booking/<int:booking_id>/status/<str:booking_status>', BookingStatus.as_view(), name='booking-status'),
    path('api/booking/guest/<int:guest_id>/', BookingByGuestView.as_view(), name='booking-by-guest'),
]
