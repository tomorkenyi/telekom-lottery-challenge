package service

import org.jboss.logging.Logger
import java.io.BufferedReader
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: BufferedReader, drawnNumbers: List<String>) {

        val winners: MutableMap<Int, Int> = HashMap()
        inputNumbers.forEachLine { line ->
            line
                .split(" ")
                .count { drawnNumbers.contains(it) }
                .let {
                    winners[it] = winners.getOrDefault(it, 0) + 1
                }
        }

        logger.debug(winners)
        printResults(winners)
    }

    private fun printResults(winners: MutableMap<Int, Int>) {

        for (i in 0..5) winners.putIfAbsent(i, 0)

        val results = winners.entries
            .filter { e -> e.key > 1 }
            .joinToString(" ") { e -> e.value.toString() }

        println(results)
    }
}
