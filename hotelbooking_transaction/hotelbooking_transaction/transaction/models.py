from __future__ import unicode_literals

from django.db import models


class Transaction(models.Model):
    date_paid = models.DateTimeField()
    amount = models.DecimalField(max_digits=14, decimal_places=2)
    booking_id = models.IntegerField()
