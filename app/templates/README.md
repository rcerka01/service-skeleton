# <%= serviceName %>

### Prerequisites

* [OpenJDK 8](https://openjdk.java.net/install/)
* [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)

### Start

```
sbt run
```

Service up-running flag will be available here: [http://localhost:8080/status](http://localhost:8080/status)

## Running the tests

```
# Unit tests
sbt test 

# Integration tests
sbt it:test 
```

To generate the coverage reports run
```
sbt clean test it:test coverageReport
```

### Coding style

[Scalafmt](https://scalameta.org/scalafmt/) will run on every compile.  IntelliJ configuration [here](https://scalameta.org/scalafmt/docs/installation.html#intellij)
Configuration in [build.sbt](build.sbt)

## Built With

* [sbt](https://www.scala-sbt.org/) - build tool
* [akka-http](https://doc.akka.io/docs/akka-http/current/) - Web Framework
