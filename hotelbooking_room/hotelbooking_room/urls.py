from django.urls import path
from django.contrib import admin
from hotelbooking_room.room.views import RoomView, RoomByTypeView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/room/<str:room_number>/', RoomView.as_view()),
    path('api/room/type/<int:type_room>/', RoomByTypeView.as_view()),
]
