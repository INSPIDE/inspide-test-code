package inspide.test

import java.nio.file.Paths

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class SolutionApp(directory: String) extends Output {

  val mainFn = for {
    gpxFiles <- Future(Paths.get(directory).toFile.listFiles().toList)
    results <- Future.sequence(gpxFiles.map(GPXParser.getResults))
    csv <- Future(CSV.toCSV(results))
    out <- Future(print(csv))
  } yield out

}

object SolutionApp extends App {

  val directory = args.toList match {
    case Nil => "data"
    case dir :: _ => dir
  }

  Await.result(new SolutionApp(directory).mainFn, Duration.Inf)

}
