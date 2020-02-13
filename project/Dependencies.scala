import sbt._

object Dependencies {
  val Poi4sVersion = "0.0.2"
  val GeotoolsVersion = "22.3"

  val ScalatestVersion = "3.1.0"

  val coreMainDependencies = Seq(
    "software.purpledragon" %% "poi4s-gpx" % Poi4sVersion % Compile,
    "org.geotools" % "gt-referencing" % GeotoolsVersion % Compile,
  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % ScalatestVersion % Test,
  )

  val coreDependencies = coreMainDependencies ++ testDependencies
  val coreDependencyOverrides = Seq()

}
