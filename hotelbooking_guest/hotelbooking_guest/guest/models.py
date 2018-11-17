from __future__ import unicode_literals

from django.db import models


class Guest(models.Model):
    name = models.CharField(max_length=50)
    email = models.EmailField()
    identity_number = models.CharField(max_length=30)
    mobile_phone = models.CharField(max_length=14)
