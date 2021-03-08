.PHONY := build

BookDB.class: BookDB.java
	javac BookDB.java

build: BookDB.class

run: build
	java -cp .:hsqldb.jar BookDB
