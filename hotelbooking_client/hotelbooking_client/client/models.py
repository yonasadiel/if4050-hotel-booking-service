from django.db import models


class Client(models.Model):
    name = models.CharField(max_length=20)
    token = models.CharField(max_length=20)
