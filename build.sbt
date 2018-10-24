import com.typesafe.config.ConfigFactory

name := """dealab-api"""

version := "1.0"
lazy val `dealab-api` = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.11.11"

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
version := conf.getString("app.version")

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.auth0" % "java-jwt" % "3.1.0",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "com.google.firebase" % "firebase-admin" % "4.1.7")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

playEbeanModels in Compile := Seq("com.zinios.dealab.models.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)
playEbeanModels in Test := Seq("com.zinios.dealab.models.*")

playEbeanModels in Test := Seq("com.zinios.dealab.models.*")
routesGenerator := InjectedRoutesGenerator
resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)
fork in run := false