scalaVersion := "2.13.6"

val applicationDependencies = {

  val akkaVersion = "2.6.17"
  val akkaHttpVersion = "10.2.6"
  val zioVersion = "1.0.12"
  val parCollectionsVersion = "1.0.4"
  val jodaTimeVersion = "2.10.12"
  val jodaConvertVersion = "2.2.1"
  val kamonVersion = "2.2.3"
  val scalaTestVersion = "3.2.10"
  val wireMockVersion = "2.27.2"

  Seq(
    "com.typesafe.akka" %% "akka-actor"           % akkaVersion,
    "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

    "dev.zio" %% "zio"         % zioVersion,
    "dev.zio" %% "zio-streams" % zioVersion,

    "org.scala-lang.modules" %% "scala-parallel-collections" % parCollectionsVersion,

    "joda-time" %  "joda-time"    % jodaTimeVersion,
    "org.joda"  %  "joda-convert" % jodaConvertVersion,

    "io.kamon" %% "kamon-core"      % kamonVersion,
    "io.kamon" %% "kamon-statsd"    % kamonVersion,
    "io.kamon" %% "kamon-akka-http" % kamonVersion,

    "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % "it,test",
    "com.typesafe.akka" %% "akka-stream-testkit"      % akkaVersion     % "it,test",
    "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % "it,test",
    "com.typesafe.akka" %% "akka-testkit"             % akkaVersion     % "it,test",

    "org.scalatest" %% "scalatest" % scalaTestVersion % "it,test",

    "com.github.tomakehurst" % "wiremock" % wireMockVersion % "it,test"
  )
}

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

coverageEnabled := true

val `test-all` = taskKey[Unit]("Run Unit tests, integration tests and scalastyle.")
val testSettings = IntegrationTests.settings ++ Seq(
  `test-all` in Compile := Def.sequential(
    test in Test,
    test in IntegrationTests.ItTest
  ).value
)

lazy val `<%= serviceName %>` = (project in file("."))
  .configs(IntegrationTests.ItTest)
  .settings(
    organization := "",
    name := "<%= serviceName %>",
    libraryDependencies ++= applicationDependencies,
    javaOptions in run ++= Seq(
      "-Dconfig.resource=application.dev.conf"),
    fork in run := true
  )
  .settings(testSettings)
