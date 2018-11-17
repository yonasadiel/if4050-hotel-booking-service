from rest_framework import serializers
from hotelbooking_review.review.models import Review


class ReviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = Review
        fields = ('text', 'rating')
        read_only_fields = ('booking_id', 'is_approved')
