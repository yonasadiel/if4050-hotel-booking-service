from __future__ import unicode_literals

from django.db import models


class RoomType(models.Model):
    name = models.CharField(max_length=10)
    price = models.DecimalField(max_digits=14, decimal_places=2)


class Room(models.Model):
    room_number = models.CharField(max_length=3, null=False)
    type_room = models.ForeignKey(to=RoomType, related_name='rooms', on_delete=models.CASCADE)
