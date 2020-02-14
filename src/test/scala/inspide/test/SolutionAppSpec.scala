package inspide.test


import org.scalatest.featurespec.AnyFeatureSpecLike

import scala.concurrent.Await
import scala.concurrent.duration._

class SolutionAppSpec extends AnyFeatureSpecLike {

  trait FakeOutput extends Output {
    var out = ""
    override def print(str: String) = out = out + str
  }

  Scenario("Generate results") {
    val app = new SolutionApp("src/test/resources") with FakeOutput
    Await.result(app.mainFn, 30 seconds)
    assert(app.out == StringContext.treatEscapes(
      """id\ttotalNumberOfPoints\ttotalDistance\tavgDistance\tminDistance\tmaxDistance
        |test.gpx\t3\t120.802\t60.401\t58.716\t62.086""".stripMargin))
  }

}
