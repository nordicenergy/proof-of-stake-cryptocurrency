#!/bin/sh
CP=conf/:classes/:lib/*:testlib/*
SP=src/java/:test/java/

if [ $# -eq 0 ]; then
TESTS="pwrc.crypto.Curve25519Test pwrc.crypto.ReedSolomonTest pwrc.peer.HallmarkTest pwrc.TokenTest pwrc.FakeForgingTest
pwrc.FastForgingTest pwrc.ManualForgingTest"
else
TESTS=$@
fi

/bin/rm -f pwrc.jar
/bin/rm -rf classes
/bin/mkdir -p classes/

javac -encoding utf8 -sourcepath ${SP} -classpath ${CP} -d classes/ src/java/pwrc/*.java src/java/pwrc/*/*.java test/java/pwrc/*.java test/java/pwrc/*/*.java || exit 1

for TEST in ${TESTS} ; do
java -classpath ${CP} org.junit.runner.JUnitCore ${TEST} ;
done



