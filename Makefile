APPNAME=geoalarms


all: build
	adb install -r target/$(APPNAME).apk

build:
	mvn install
