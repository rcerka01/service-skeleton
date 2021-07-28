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
- Unit and Integration tests using the [Specs2](https://etorreborre.github.io/specs2/guide/SPECS2-3.8.5/org.specs2.guide.QuickStart.html) framework
- [Scalastyle](http://www.scalastyle.org/) SBT plugin for checking code style
- [Scoverage](http://scoverage.org/) SBT plugin for checking code coverage of the tests
- [rms-speculate](https://github.com/bbc/rms-speculate/) SBT plugin for creating service scripts and building the RPM on Centos 7
