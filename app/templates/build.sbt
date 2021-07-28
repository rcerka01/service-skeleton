import scala.util.Properties

scalaVersion := "2.11.12"

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-encoding",
  "utf8"
)

val applicationDependencies = {
  val akkaVersion               = "2.5.23"
  val akkaStreamTestkit         = "2.5.9"
  val akkaHttpVersion           = "10.1.9"
  val akkaHttpJson4sVersion     = "1.27.0"
  val json4sVersion             = "3.6.7"
  val jodaTimeVersion           = "2.9.4"
  val jodaConvertVersion        = "1.8"
  val logbackVersion            = "1.2.1"
  val slf4sVersion              = "1.7.12"
  val kamonVersion              = "0.6.7"
  val specs2Version             = "3.8.9"
  val wireMockVersion           = "2.6.0"

  Seq(
    "com.typesafe.akka"      %% "akka-actor"          % akkaVersion,
    "com.typesafe.akka"      %% "akka-slf4j"          % akkaVersion,
    "com.typesafe.akka"      %% "akka-stream"         % akkaVersion,
    "com.typesafe.akka"      %% "akka-http"           % akkaHttpVersion,
    "de.heikoseeberger"      %% "akka-http-json4s"    % akkaHttpJson4sVersion,
    "org.json4s"             %% "json4s-jackson"      % json4sVersion,
    "org.json4s"             %% "json4s-ext"          % json4sVersion,
    "joda-time"              %  "joda-time"           % jodaTimeVersion,
    "org.joda"               %  "joda-convert"        % jodaConvertVersion,
    "ch.qos.logback"         %  "logback-classic"     % logbackVersion,
    "org.slf4s"              %% "slf4s-api"           % slf4sVersion,
    "io.kamon"               %% "kamon-core"          % kamonVersion,
    "io.kamon"               %% "kamon-statsd"        % kamonVersion,
    "io.kamon"               %% "kamon-akka-http"     % kamonVersion,
    "com.typesafe.akka"      %% "akka-http-testkit"   % akkaHttpVersion    % "test",
    "com.typesafe.akka"      %% "akka-stream-testkit" % akkaStreamTestkit  % "test",
    "org.specs2"             %% "specs2-core"         % specs2Version      % "it,test",
    "org.specs2"             %% "specs2-mock"         % specs2Version      % "it,test",
    "com.github.tomakehurst" %  "wiremock"            % wireMockVersion    % "it,test"
  )
}

dependencyCheckFormats := Seq("HTML", "XML")
dependencyCheckCveUrlModified := Some(new URL("http://vulnerability-mirror.rms.api.bbci.co.uk/data/nvdcve-1.0-modified.json.gz"))
dependencyCheckCveUrlBase := Some("http://vulnerability-mirror.rms.api.bbci.co.uk/data/nvdcve-1.0-%d.json.gz")
dependencyCheckFailBuildOnCVSS := 5
dependencyCheckSuppressionFiles += new File("dependency-suppressions.xml")

val `test-all` = taskKey[Unit]("Run Unit tests, integration tests and scalastyle.")
val testSettings = IntegrationTests.settings ++ Seq(
  `test-all` in Compile := Def.sequential(
    test in Test,
    test in IntegrationTests.ItTest,
    dependencyCheck
  ).value
)

lazy val `<%= serviceName %>` = (project in file("."))
  .configs(IntegrationTests.ItTest)
  .settings(
    organization := "",
    name := "<%= serviceName %>",
    libraryDependencies ++= applicationDependencies,
    javaOptions in run ++= Seq(
      "-Dconfig.resource=application.dev.conf",
      "-Dlogback.configurationFile=logback.dev.xml"),
    fork in run := true
  )
  .settings(testSettings)
