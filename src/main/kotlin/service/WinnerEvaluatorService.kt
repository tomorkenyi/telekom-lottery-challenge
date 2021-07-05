package service

import org.jboss.logging.Logger
import util.ValidatorUtil
import java.io.BufferedReader
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: BufferedReader, drawnNumbers: List<String>): String {
        val winners = HashMap<Int, Int>()
        checkNumbers(inputNumbers, drawnNumbers, winners)
        return getResults(winners)
    }

    private fun checkNumbers(inputNumbers: BufferedReader, drawnNumbers: List<String>, winners: MutableMap<Int, Int>) {
        val regex = ValidatorUtil.pattern.toRegex()
        inputNumbers.forEachLine { line ->
            when {
                regex.matches(line) ->
                    line.split(" ")
                        .count { drawnNumbers.contains(it) }
                        .let { winners.merge(it, 1, Int::plus) }
                else -> logger.debug("Pattern is not right for line: $line, so skipping")
            }
        }
        logger.debug(winners)
    }

    private fun getResults(winners: MutableMap<Int, Int>): String {
        (0..5).forEach { winners.putIfAbsent(it, 0) }

        val results = winners.entries
            .filter { e -> e.key > 1 }
            .joinToString(" ") { e -> e.value.toString() }

        println(results)
        return results
    }
}

