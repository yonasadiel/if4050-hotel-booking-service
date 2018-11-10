from rest_framework import serializers
from hotelbooking_transaction.transaction.models import Transaction


class TransactionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Transaction
        fields = ('date_paid', 'amount')
        read_only_fields = ('booking_id', )
