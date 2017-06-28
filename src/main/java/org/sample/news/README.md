# News application

The news package is the application as a whole (including all dependencies), currently the entry-point
instantiate a server and injects a service into it and starts the server, This usually is not the case where the entry
point executes a root command of the application and then it runs the part of the application need to be run.

**Packages**
- `application`: The package represent the domain application module, where the domain **commands** and **queries** are
existing as well as the domain service implementation

- `domain`: The core domain where the **entities** and **aggregate root** are living, also the interfaces of the 
repository and the service. The domain declares the outline of the repository and the service, and its a MUST for the
application/infrastructure to implement this interface for the domain to function.

- `infrastructure`: The implementation of the required infrastructure for the domain e.g. (message bus, DB, etc..)

- `protocol`: The service outline and the value object of the service commands/queries written in protocol buffers

- `server`: The server implementation for the news application

### Simple diagram
```
               +--------------+
               |    Server    |
               +------+-------+
                      |
                      v
               +------+--------+
           +--->   Application <-----------+
           |   |    Service    +--------+  |
           |   |               |        |  |
           |   +^----------+---+        |  |
           |               |      +-----v--+-+
 +---------+--+     +------v---+  |          |
 | Event      |     | Event    |  |Repository|
 | Subscriber +--+--+ Publisher|  |          |
 +------------+  |  +----------+  +-------^--+
                 |                        |
 +---------------v-+  +-------------------+---+
 | Transport layer |  |  Entities/Aggregates  |
 +--+----------^---+  +-----------------------+
    |          |
+v--v----------+------------------------------+
|                                             |
|          SYSTEM            BUS              |
|                                             |
|                                             |
|                                             |
+---------------------------------------------+

```