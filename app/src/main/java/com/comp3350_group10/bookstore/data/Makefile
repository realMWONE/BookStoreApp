.PHONY := build

ConnectDatabase.class: ConnectDatabase.java
	javac ConnectDatabase.java

build: ConnectDatabase.class

run: build
	java -cp .:hsqldb.jar ConnectDatabase

