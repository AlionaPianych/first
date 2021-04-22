import sbt._

object Dependencies {
  
  object Version {
    val `scala3-compiler`             = "3.0.0-RC2"
    val cats                          = "2.5.0"
    val `cats-effect`                 = "3.0.2"
    val munit                         = "0.7.23"
  }

  lazy val cats: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-laws",
    "org.typelevel" %% "cats-free",
    "org.typelevel" %% "cats-testkit",
    "org.typelevel" %% "alleycats-core"
  ).map(_ % Version.cats withSources() withJavadoc())

  lazy val `cats-effect`: Seq[ModuleID] = Seq(
    "org.typelevel" %% "cats-effect"
  ).map(_ % Version.`cats-effect` withSources() withJavadoc())
  
  lazy val munit: Seq[ModuleID] = Seq(
    "org.scalameta" %% "munit",
    "org.scalameta" %% "munit-scalacheck"
  ).map(_ % Version.munit % Test withSources() withJavadoc())  

}
