package inspide

package object test {

  case class Point(lat: Double, lon: Double)
  case class Segment(from: Point, to: Point)

  case class Results(totalNumberOfPoints: Int, totalDistance: Double, avgDistance: Double, minDistance: Double, maxDistance: Double)

}
