package inspide.test

object CSV {

  implicit val ordering = Ordering[Double].reverse

  val CsvHead = "id\ttotalNumberOfPoints\ttotalDistance\tavgDistance\tminDistance\tmaxDistance\n"

  val results2csv = (results: Results) => results.productIterator.mkString("\t")

  val toCSV = (seqOfResults: Seq[Results]) => CsvHead + seqOfResults.sortBy(_.totalDistance).map(results2csv).mkString("\n")

}
