package service

import org.jboss.logging.Logger
import java.io.BufferedReader
import java.util.stream.Collectors.joining
import java.util.stream.LongStream
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: BufferedReader, drawnNumbers: List<String>) {

        val winners: MutableMap<Int, Int> = HashMap()
        inputNumbers.forEachLine { line ->
            val guessed = line.split(" ").count { drawnNumbers.contains(it) }
            if (winners.computeIfPresent(guessed) { _, v -> v + 1 } == null) {
                winners[guessed] = 1
            }
        }

        logger.debug(winners)

        printResults(winners)
    }

    private fun printResults(winners: MutableMap<Int, Int>) {

        for (i in 0..5) winners.putIfAbsent(i, 0)

        val results = winners.entries
            .stream()
            .filter { e -> e.key > 1 }
            .map { e -> e.value.toString() }
            .collect(joining(" "))

        println(results)
    }
}
