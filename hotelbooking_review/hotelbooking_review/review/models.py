from django.db import models


class Review(models.Model):
    booking_id = models.IntegerField()
    text = models.TextField()
    rating = models.IntegerField()
    is_approved = models.BooleanField(default=False)
