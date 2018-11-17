from rest_framework import serializers
from hotelbooking_guest.guest.models import Guest


class GuestSerializer(serializers.ModelSerializer):
    class Meta:
        model = Guest
        fields = ('id', 'name', 'email', 'identity_number', 'mobile_phone')
        read_only_fields = ('id', )
