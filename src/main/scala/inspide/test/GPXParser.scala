package inspide.test

import java.io.{File, FileInputStream}

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

  val getPoints = (file: File) => for {
    fileParser <- Future(new GPXFileParser)
    is <- createInputStream(file)
    gpx <- Future(fileParser.parseGPX(is))
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
  } yield Results(totalPoints, totalDistance, avgDistance, minDistance, maxDistance)

}
