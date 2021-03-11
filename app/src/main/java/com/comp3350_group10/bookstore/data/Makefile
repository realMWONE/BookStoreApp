.PHONY := build

BookDatabase.class: BookDatabase.java
	javac BookDatabase.java

build: BookDatabase.class

run: build
	java -cp .:hsqldb.jar BookDatabase

