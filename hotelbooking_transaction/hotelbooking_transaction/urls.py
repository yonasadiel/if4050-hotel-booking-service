from django.urls import path
from django.contrib import admin
from hotelbooking_transaction.transaction.views import TransactionView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/transaction/<str:booking_id>/', TransactionView.as_view()),
]
