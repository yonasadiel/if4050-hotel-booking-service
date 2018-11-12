from django.urls import path
from django.contrib import admin
from hotelbooking_booking.booking.views import ListBookingView, DetailBookingView, BookingByGuestView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/booking/', ListBookingView.as_view()),
    path('api/booking/<int:pk>/', DetailBookingView.as_view()),
    path('api/booking/guest/<int:guest_id>/', BookingByGuestView.as_view()),
]
