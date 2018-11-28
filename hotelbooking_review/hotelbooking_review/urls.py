from django.conf import settings
from django.conf.urls.static import static
from django.contrib import admin
from django.urls import path
from hotelbooking_review.review.views import DetailReviewView, ListReviewView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/review/', ListReviewView.as_view()),
    path('api/review/<str:booking_id>/', DetailReviewView.as_view()),
] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
