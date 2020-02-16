import Dependencies._

organization := "inspide"
name := "solution"
version := "1.0.0"

scalaVersion := "2.12.10"

assemblyJarName in assembly := "solution.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

resolvers += "Bintray" at "https://jcenter.bintray.com/"
resolvers += "Osgeo Repo" at "http://download.osgeo.org/webdav/geotools/"
resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

libraryDependencies ++= coreDependencies
dependencyOverrides ++= coreDependencyOverrides
