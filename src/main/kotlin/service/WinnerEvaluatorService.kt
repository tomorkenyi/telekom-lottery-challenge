package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
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
        inputNumbers.forEach { line ->
            when {
                validate(line) -> {
                    line.split(" ")
                        .count { drawnNumbers.contains(it) }
                        .let { winners.merge(it, 1, Int::plus) }
                }
                else -> logger.debug("Value $line is invalid, skipping.")
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

