from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from django.contrib import admin
from hotelbooking_guest.guest.views import ListGuestView, DetailGuestView, DetailGuestByIdNumberView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/guest/', ListGuestView.as_view()),
    path('api/guest/<int:pk>/', DetailGuestView.as_view()),
    path('api/guest/identity_number/<str:identity_number>/', DetailGuestByIdNumberView.as_view()),
] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
