#!/bin/sh
if [ -e ~/.pwrc/pwrc.pid ]; then
    PID=`cat ~/.pwrc/pwrc.pid`
    ps -p $PID > /dev/null
    STATUS=$?
    echo "stopping"
    while [ $STATUS -eq 0 ]; do
        kill `cat ~/.pwrc/pwrc.pid` > /dev/null
        sleep 5
        ps -p $PID > /dev/null
        STATUS=$?
    done
    rm -f ~/.pwrc/pwrc.pid
    echo "Pwrc server stopped"
fi

