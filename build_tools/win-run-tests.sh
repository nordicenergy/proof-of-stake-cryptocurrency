#!/bin/sh
CP="conf/;classes/;lib/*;testlib/*"
SP="src/java/;test/java/"
TESTS="pwrc.crypto.Curve25519Test pwrc.crypto.ReedSolomonTest"

/bin/rm -f pwrc.jar
/bin/rm -rf classes
/bin/mkdir -p classes/

javac -encoding utf8 -sourcepath $SP -classpath $CP -d classes/ src/java/pwrc/*.java src/java/pwrc/*/*.java test/java/pwrc/*/*.java || exit 1

java -classpath $CP org.junit.runner.JUnitCore $TESTS

