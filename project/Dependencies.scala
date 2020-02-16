import sbt._

object Dependencies {
  val GPXParserVersion = "1.12"
  val GeotoolsVersion = "22.3"

  val ScalatestVersion = "3.1.0"

  val coreMainDependencies = Seq(
    "me.himanshusoni.gpxparser" % "gpx-parser" % GPXParserVersion % Compile,
    "org.geotools" % "gt-referencing" % GeotoolsVersion % Compile,

    /**
     * The next line avoids a problem downloading this transitive dependency.
     * Please check https://stackoverflow.com/a/26993223
     */
    "javax.media" % "jai_core" % "1.1.3" % Compile from "http://download.osgeo.org/webdav/geotools/javax/media/jai_core/1.1.3/jai_core-1.1.3.jar"
  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % ScalatestVersion % Test,
  )

  val coreDependencies = coreMainDependencies ++ testDependencies
  val coreDependencyOverrides = Seq()

}
