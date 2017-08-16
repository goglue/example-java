# example-java

This project is an example for an articles micro-service using DDD methodology. The idea behind the implementation to 
have a free domain (Plug & Play style) with no vendor locking where this application can be used anywhere. As web app, 
cli, etc.. The business logic is totally decoupled and abstracted in each layer to gain more dynamic components as 
well as better maintainability and testability.

### The packages

Inside each package that contain classes there is a `README.md` file where it explain about that package

### Installation

**Dependencies**:
- Java
- Maven
- Make (optional)

**Install dependencies**
```
~$ mvn install
```
or
```
~$ make install
```

**Run tests**
```
~$ mvn test
```
or
```
~$ make test
```

**Compile protocol buffer service and types**
```
~$ mvn protobuf:compile && mvn protobuf:compile-custom
```
or
```
~$ make proto
```