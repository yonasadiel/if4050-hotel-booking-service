from hotelbooking_transaction.transaction.models import Transaction
from hotelbooking_transaction.transaction.serializers import TransactionSerializer
from rest_framework import views
from rest_framework.response import Response


class TransactionView(views.APIView):

    def get(self, request, booking_id):
        transactions = Transaction.objects.filter(booking_id=booking_id)
        return Response(TransactionSerializer(transactions, many=True).data)

    def post(self, request, booking_id):
        request_serializer = TransactionSerializer(data=request.data)
        request_serializer.is_valid(raise_exception=True)

        transaction = Transaction()
        transaction.date_paid = request_serializer.validated_data.get('date_paid', None)
        transaction.amount = request_serializer.validated_data.get('amount', None)
        transaction.booking_id = booking_id
        transaction.save()

        transactions = Transaction.objects.filter(booking_id=booking_id)
        return Response(TransactionSerializer(transactions, many=True).data)
