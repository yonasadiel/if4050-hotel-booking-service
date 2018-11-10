from django.contrib import admin
from hotelbooking_client.client.models import Client


@admin.register(Client)
class ClientAdmin(admin.ModelAdmin):
    list_display = ['name', 'token']
    search_fields = ['name', 'token']
