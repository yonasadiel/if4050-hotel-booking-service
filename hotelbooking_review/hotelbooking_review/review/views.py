from hotelbooking_review.review.models import Review
from hotelbooking_review.review.serializers import ReviewSerializer
from rest_framework import views, status
from rest_framework.response import Response


class ListReviewView(views.APIView):

    def get(self, request):
        reviews = Review.objects.filter(is_approved=True)
        return Response(ReviewSerializer(reviews, many=True).data)


class DetailReviewView(views.APIView):

    def get(self, request, booking_id):
        review = Review.objects.filter(booking_id=booking_id).first()
        if review is None:
            return Response({}, status=status.HTTP_404_NOT_FOUND)
        return Response(ReviewSerializer(review).data)

    def post(self, request, booking_id):
        request_serializer = ReviewSerializer(data=request.data)
        request_serializer.is_valid(raise_exception=True)

        review = Review.objects.filter(booking_id=booking_id).first()
        if review is not None:
            return Response({}, status=status.HTTP_400_BAD_REQUEST)

        review = Review()
        review.text = request_serializer.validated_data.get('text', None)
        review.rating = request_serializer.validated_data.get('rating', None)
        review.is_approved = False
        review.booking_id = booking_id
        review.save()

        reviews = Review.objects.filter(booking_id=booking_id)
        return Response(ReviewSerializer(reviews, many=True).data)
