from __future__ import unicode_literals

from django.db import models


class Booking(models.Model):
    check_in = models.DateField()
    check_out = models.DateField()
    payment_name = models.CharField(max_length=100, null=True, blank=True)
    payment_type = models.CharField(max_length=20, null=True, blank=True)
    price = models.DecimalField(max_digits=14, decimal_places=2)
    room_number = models.CharField(max_length=3, null=True)
