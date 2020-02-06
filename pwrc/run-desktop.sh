#!/bin/sh
if [ -x jre/bin/java ]; then
    JAVA=./jre/bin/java
else
    JAVA=java
fi
${JAVA} -cp classes:lib/*:conf:addons/classes:addons/lib/* -Dpwrc.runtime.mode=desktop -Dpwrc.runtime.dirProvider=pwrc.env.DefaultDirProvider pwrc.Pwrc
