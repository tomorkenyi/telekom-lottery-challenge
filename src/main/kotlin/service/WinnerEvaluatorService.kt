package service

import org.jboss.logging.Logger
import util.ValidatorUtil
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: MutableList<String>, drawnNumbers: List<String>): String {
        val winners = HashMap<Int, Int>()
        checkNumbers(inputNumbers, drawnNumbers, winners)
        return getResults(winners)
    }

    private fun checkNumbers(inputNumbers: MutableList<String>, drawnNumbers: List<String>, winners: MutableMap<Int, Int>) {
        val regex = ValidatorUtil.pattern.toRegex()
        inputNumbers.forEach { line ->
            when {
                regex.matches(line) && !ValidatorUtil.hasDuplicates(line) ->
                    line.split(" ")
                        .count { drawnNumbers.contains(it) }
                        .let { winners.merge(it, 1, Int::plus) }
                else -> logger.debug("Pattern is not right for line or it has duplicate elements: $line, so skipping")
            }
        }
        logger.debug(winners)
    }

    private fun getResults(winners: MutableMap<Int, Int>): String {
        return winners
            .also { (0..5).forEach { winners.putIfAbsent(it, 0) } }.entries
            .filter { e -> e.key > 1 }
            .joinToString(" ") { e -> e.value.toString() }
            .also {
                println(it)
            }
    }
}

