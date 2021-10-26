# service-skeleton

[Yeoman](http://yeoman.io/) generator for API service.

## Installation

First install Yeoman:
```
npm install -g yo
```
Then make the service generator available to Yeoman with:
```
cd service-skeleton
npm install
npm link
```

## Usage

Create your application directory and auto-generate the project

```
cd workspace
mkdir new-service
cd new-service
yo service
```
Yeoman will then ask you a number of questions about what you want from the service before generating the project template.

## What's included

- [Akka HTTP](http://doc.akka.io/docs/akka-http/current/scala/http/index.html) webserver
- Unit and Integration tests using the ScalaTest
- [Scalastyle](http://www.scalastyle.org/) SBT plugin for checking code style
- [Scoverage](http://scoverage.org/) SBT plugin for checking code coverage of the tests

## Backlog

Down-tag warnings or use Node version < 12.0.0

```
sudo n 12.0.0
```
