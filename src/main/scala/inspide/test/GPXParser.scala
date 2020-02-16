package inspide.test

import java.io.{File, FileInputStream, InputStream}

import inspide.test.DistanceCalculator._
import me.himanshusoni.gpxparser.modal.{GPX, Track, TrackSegment}
import me.himanshusoni.gpxparser.{GPXParser => GPXFileParser}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object GPXParser {

  private val createInputStream = (file: File) => Future {
    new FileInputStream(file)
  }

  private val toPoints = (trackSegment: TrackSegment) => trackSegment.getWaypoints.asScala.toList.map(wp => Point(wp.getLatitude, wp.getLongitude))

  private val toListOfPoints = (track: Track) => track.getTrackSegments.asScala.toList.map(toPoints).head

  private val toListOfTrackSegments = (tracks: Seq[Track]) => tracks.map(toListOfPoints).head

  private val toListOfTracks = (gpx: GPX) => gpx.getTracks.asScala.toList

  private val gpxParser = new GPXFileParser()

  /**
   * This must be done because GPXFileParser is not thread-safe. Internally it uses a SimpleDateFormat that is created
   * statically. SimpleDataFormat is not thread-safe and the usage of Futures ends up with a lot of stack traces. I would
   * use Task of Monix or IO from Cats instead of plain Scala Futures. But I wanted to strictly stick to the definition
   * of this problem, so I have not use any other libraries.
   * The conflictive line is at [[https://github.com/himanshu-soni/gpx-parser/blob/master/src/main/java/me/himanshusoni/gpxparser/BaseGPX.java#L15]]
   */
  private val parseGPX = (inputStream: InputStream) => Future {
    gpxParser.synchronized {
      gpxParser.parseGPX(inputStream)
    }
  }

  val getPoints = (file: File) => for {
    is <- createInputStream(file)
    gpx <- parseGPX(is)
    tracks <- Future(toListOfTracks(gpx))
    trackPoints <- Future(toListOfTrackSegments(tracks))
    _ <- Future(is.close())
  } yield trackPoints

  val getResults = (file: File) => for {
    points <- getPoints(file)
    segments <- Future(toSegments(points))
    totalPoints <- Future(points.size)
    totalDistance <- Future(calculateTotalDistance(segments))
    avgDistance <- Future(calculateAverageDistance(segments))
    minDistance <- Future(getMinimunDistance(segments))
    maxDistance <- Future(getMaximunDistance(segments))
  } yield Results(file.getName, totalPoints, totalDistance, avgDistance, minDistance, maxDistance)

}
