package inspide.test

import java.nio.file.Paths


import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpecLike

import scala.concurrent.Await
import scala.concurrent.duration._


class GPXParserSpec extends AnyFeatureSpecLike with GivenWhenThen {

  private val ExpectedSegments = List(
    Point(40.492861, -3.593716),
    Point(40.492528, -3.593178),
    Point(40.493062, -3.593395)
  )

  private val ExpectedResults = Results(
    3,
    58.716 + 62.086,
    (58.716 + 62.086) / 2,
    58.716,
    62.086
  )

  Feature("Parse GPX files") {

    Scenario("Parse GPX file") {
      Given("a GPX file")
      val file = Paths.get("src/test/resources", "test.gpx").toFile
      When("the points are required")
      val points = GPXParser.getPoints(file)
      Then("the segments are the expected")
      assert(Await.result(points, 5 seconds) == ExpectedSegments)
    }

    Scenario("Get results for file") {
      Given("a GPX file")
      val file = Paths.get("src/test/resources", "test.gpx").toFile
      When("the results are required")
      val results = GPXParser.getResults(file)

      Then("the results are the expected")
      assert(Await.result(results, 5 seconds) == ExpectedResults)
    }

  }

}
