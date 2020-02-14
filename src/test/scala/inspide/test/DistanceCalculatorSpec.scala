package inspide.test

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpecLike

/**
 * The distance fixtures have been calculated at https://edwilliams.org/gccalc.htm
 */
class DistanceCalculatorSpec extends AnyFeatureSpecLike with GivenWhenThen {

  import inspide.test.DistanceCalculator._

  Feature("Calculate distances") {

    Scenario("Distance between points") {
      Given("three points")
      val point1 = Point(40.492861, -3.593716)
      val point2 = Point(40.492528, -3.593178)
      val point3 = Point(40.493062, -3.593395)
      When("the distance between two of them is calculated")
      val distanceFrom1To2 = calculateDistance(Segment(point1, point2))
      val distanceFrom2To3 = calculateDistance(Segment(point2, point3))
      Then("the results are the expected")
      assert(distanceFrom1To2 == 58.716)
      assert(distanceFrom2To3 == 62.086)
    }

    Scenario("Total distance between points") {
      Given("three points")
      val point1 = Point(40.492861, -3.593716)
      val point2 = Point(40.492528, -3.593178)
      val point3 = Point(40.493062, -3.593395)
      When("the total distance is calculated")
      val segments = toSegments(Seq(point1, point2, point3))
      val distance = calculateTotalDistance(segments)
      Then("the results are the expected")
      assert(distance == 58.716 + 62.086)
    }

    Scenario("Average distance between points") {
      Given("three points")
      val point1 = Point(40.492861, -3.593716)
      val point2 = Point(40.492528, -3.593178)
      val point3 = Point(40.493062, -3.593395)
      When("the average distance is calculated")
      val segments = toSegments(Seq(point1, point2, point3))
      val avgDistance = calculateAverageDistance(segments)
      Then("the results are the expected")
      assert(avgDistance == (58.716 + 62.086) / 2)
    }

    Scenario("Minimum distance between points") {
      Given("three points")
      val point1 = Point(40.492861, -3.593716)
      val point2 = Point(40.492528, -3.593178)
      val point3 = Point(40.493062, -3.593395)
      When("the minimum distance is calculated")
      val segments = toSegments(Seq(point1, point2, point3))
      val minDistance = getMinimunDistance(segments)
      Then("the results are the expected")
      assert(minDistance == 58.716)
    }

    Scenario("Maximum distance between points") {
      Given("three points")
      val point1 = Point(40.492861, -3.593716)
      val point2 = Point(40.492528, -3.593178)
      val point3 = Point(40.493062, -3.593395)
      When("the maximum distance is calculated")
      val segments = toSegments(Seq(point1, point2, point3))
      val maxDistance = getMaximunDistance(segments)
      Then("the results are the expected")
      assert(maxDistance == 62.086)
    }

  }

}
