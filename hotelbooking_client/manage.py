#!/usr/bin/env python
import os
import sys

if __name__ == "__main__":
    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "hotelbooking_client.settings")

    from django.core.management import execute_from_command_line

    # Override default port for `runserver` command
    import django
    django.setup()
    from django.core.management.commands.runserver import Command as runserver
    runserver.default_port = "8002"

    execute_from_command_line(sys.argv)
