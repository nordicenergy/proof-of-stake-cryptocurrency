#!/bin/sh
java -cp classes pwrc.tools.ManifestGenerator
/bin/rm -f pwrc.jar
jar cfm pwrc.jar resource/pwrc.manifest.mf -C classes . || exit 1
/bin/rm -f pwrcservice.jar
jar cfm pwrcservice.jar resource/nxtservice.manifest.mf -C classes . || exit 1

echo "jar files generated successfully"
