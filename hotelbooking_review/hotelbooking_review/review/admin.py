from django.contrib import admin
from hotelbooking_review.review.models import Review


@admin.register(Review)
class ReviewAdmin(admin.ModelAdmin):
    list_display = ['booking_id', 'rating', 'text', 'is_approved']
