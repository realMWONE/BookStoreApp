.PHONY := build

BookDatabase.class: BookDatabase.java
	javac BookDatabase.java

build: BookDatabase.class

run: build
	java -cp .:hsqldb-2.5.1.jar BookDatabase
