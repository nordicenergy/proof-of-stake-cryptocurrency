#!/bin/sh
VERSION=$1
if [ -x ${VERSION} ];
then
	echo VERSION not defined
	exit 1
fi
PACKAGE=pwrc-client-${VERSION}.zip
echo PACKAGE="${PACKAGE}"

FILES="changelogs classes conf html lib src resource addons"
FILES="${FILES} pwrc.jar pwrcservice.jar"
FILES="${FILES} 3RD-PARTY-LICENSES.txt AUTHORS.txt LICENSE.txt JPL-NRS.pdf"
FILES="${FILES} DEVELOPERS-GUIDE.md OPERATORS-GUIDE.md README.md README.txt USERS-GUIDE.md"
FILES="${FILES} mint.bat mint.sh run.bat run.sh run-tor.sh run-desktop.sh start.sh stop.sh compact.sh compact.bat sign.sh sign.bat passphraseRecovery.sh passphraseRecovery.bat"
FILES="${FILES} pwrc.policy nxtdesktop.policy NXT_Wallet.url"
FILES="${FILES} compile.sh javadoc.sh jar.sh package.sh"
FILES="${FILES} win-compile.sh win-javadoc.sh win-package.sh"

echo compile
./win-compile.sh
echo jar
./jar.sh
echo javadoc
rm -rf html/doc/*
./win-javadoc.sh

rm -rf pwrc
rm -rf ${PACKAGE}
mkdir -p pwrc/
mkdir -p pwrc/logs
echo copy resources
cp -a ${FILES} pwrc
echo gzip
for f in `find pwrc/html -name *.gz`
do
	rm -f "$f"
done
for f in `find pwrc/html -name *.html -o -name *.js -o -name *.css -o -name *.json -o -name *.ttf -o -name *.svg -o -name *.otf`
do
	gzip -9c "$f" > "$f".gz
done
echo zip
zip -q -X -r ${PACKAGE} pwrc -x \*/.idea/\* \*/.gitignore \*/.git/\* \*/\*.log \*.iml pwrc/conf/pwrc.properties pwrc/conf/logging.properties pwrc/conf/localstorage/\*
rm -rf pwrc
echo done