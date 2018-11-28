from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from django.contrib import admin
from hotelbooking_transaction.transaction.views import TransactionView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/transaction/<str:booking_id>/', TransactionView.as_view()),
] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
