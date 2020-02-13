import Dependencies._

organization := "inspide"
name := "solution"
version := "1.0.0"

scalaVersion := "2.12.10"

enablePlugins(JavaAppPackaging)


resolvers += "Osgeo Repo" at "http://download.osgeo.org/webdav/geotools/"
resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

libraryDependencies ++= coreDependencies
dependencyOverrides ++= coreDependencyOverrides
