#!/bin/sh
java -cp "classes:lib/*:conf" pwrc.tools.SignTransactionJSON $@
exit $?
