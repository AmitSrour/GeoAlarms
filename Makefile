APPNAME=geoalarms


all: build deploy

deploy:
	adb install -r target/$(APPNAME).apk

build:
	mvn install
