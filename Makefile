TOP ?= $(shell pwd)

test:
	mvn test
install:
	mvn install
proto: install
	mvn protobuf:compile && mvn protobuf:compile-custom
