from django.urls import path
from django.contrib import admin
from hotelbooking_room.room.views import RoomView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/room/<str:room_number>/', RoomView.as_view()),
]
