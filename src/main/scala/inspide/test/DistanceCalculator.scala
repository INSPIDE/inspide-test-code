package inspide.test

import org.geotools.referencing.GeodeticCalculator


object DistanceCalculator {

  private val fixDecimals = (precission: Int) => (value: Double) => BigDecimal(value).setScale(precission, BigDecimal.RoundingMode.HALF_UP).toDouble
  private val fixTo3Decimals = fixDecimals(3)

  val calculateDistance = (segment: Segment) => {
    val gc = new GeodeticCalculator()
    gc.setStartingGeographicPoint(segment.from.lon, segment.from.lat)
    gc.setDestinationGeographicPoint(segment.to.lon, segment.to.lat)
    fixTo3Decimals(gc.getOrthodromicDistance)
  }

  val toSegments = (points: Seq[Point]) => points.zip(points.tail).map(p => Segment(p._1, p._2))

  val calculateTotalDistance = (segments: Seq[Segment]) => fixTo3Decimals(segments.map(calculateDistance).sum)

  val calculateAverageDistance = (segments: Seq[Segment]) => fixTo3Decimals(calculateTotalDistance(segments) / segments.length)

  val getMinimunDistance = (segments: Seq[Segment]) => fixTo3Decimals(segments.map(calculateDistance).min)

  val getMaximunDistance = (segments: Seq[Segment]) => fixTo3Decimals(segments.map(calculateDistance).max)

}
