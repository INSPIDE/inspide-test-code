package inspide.test

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpecLike

class CSVSpec extends AnyFeatureSpecLike with GivenWhenThen {

  private val ExpectedResults = Seq(
    Results("f1.gpx", 1, 2, 3, 4, 5),
    Results("f2.gpx", 6, 7, 8, 9, 10),
  )

  Scenario("Create CSV") {
    When("the results are formatted as CSV")
    val csv = CSV.toCSV(ExpectedResults)
    Then("the csv is the expected")
    assert(csv ==
      StringContext.treatEscapes("""id\ttotalNumberOfPoints\ttotalDistance\tavgDistance\tminDistance\tmaxDistance
        |f2.gpx\t6\t7.0\t8.0\t9.0\t10.0
        |f1.gpx\t1\t2.0\t3.0\t4.0\t5.0""".stripMargin))
  }

}
