from django.contrib import admin
from hotelbooking_transaction.transaction.models import Transaction


@admin.register(Transaction)
class TransactionAdmin(admin.ModelAdmin):
    list_display = ['date_paid', 'amount', 'booking_id']
    search_fields = ['booking_id']
