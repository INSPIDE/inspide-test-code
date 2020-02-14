import sbt._

object Dependencies {
  val GPXParserVersion = "1.12"
  val GeotoolsVersion = "22.3"

  val ScalatestVersion = "3.1.0"

  val coreMainDependencies = Seq(
    "me.himanshusoni.gpxparser" % "gpx-parser" % GPXParserVersion % Compile,
    "org.geotools" % "gt-referencing" % GeotoolsVersion % Compile,
  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % ScalatestVersion % Test,
  )

  val coreDependencies = coreMainDependencies ++ testDependencies
  val coreDependencyOverrides = Seq()

}
