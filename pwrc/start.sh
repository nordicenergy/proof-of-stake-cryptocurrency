#!/bin/sh
if [ -e ~/.pwrc/pwrc.pid ]; then
    PID=`cat ~/.pwrc/pwrc.pid`
    ps -p $PID > /dev/null
    STATUS=$?
    if [ $STATUS -eq 0 ]; then
        echo "Pwrc server already running"
        exit 1
    fi
fi
mkdir -p ~/.pwrc/
DIR=`dirname "$0"`
cd "${DIR}"
if [ -x jre/bin/java ]; then
    JAVA=./jre/bin/java
else
    JAVA=java
fi
nohup ${JAVA} -cp classes:lib/*:conf:addons/classes:addons/lib/* -Dpwrc.runtime.mode=desktop pwrc.Pwrc > /dev/null 2>&1 &
echo $! > ~/.pwrc/pwrc.pid
cd - > /dev/null
