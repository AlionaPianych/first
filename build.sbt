import Dependencies._

lazy val root = project
  .in(file("."))
  .settings(name := "scala-course-2021-spring")
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= dependencies)
  .settings(testFrameworks += new TestFramework("munit.Framework"))
  .settings(Test / parallelExecution := false)

lazy val dependencies =
    cats ++
    `cats-effect` ++
    munit

lazy val commonSettings = Seq(
    scalaVersion := "3.0.0-RC2",
    organization := "karazin-scala-users-group",
    version      := "0.1.0-SNAPSHOT",
    scalacOptions ++= Seq(
        "-feature",
        "-deprecation",
        "-Xfatal-warnings",
    ),
    javacOptions ++= Seq(
        "-source", "11",
        "-target", "11"
    ),
)
