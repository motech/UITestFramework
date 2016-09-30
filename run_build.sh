#!/usr/bin/env bash

if [ "$TRAVIS_EVENT_TYPE" == "cron" ]; then
    echo "USE mysql;\nUPDATE user SET password=PASSWORD('password') WHERE user='root';\nFLUSH PRIVILEGES;\n" | mysql -u root
    mvn clean install -PFT
fi