package service

import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: MutableList<List<String>>, drawnNumbers: List<String>): String {
        val winners = HashMap<Int, Int>()
        checkNumbers(inputNumbers, drawnNumbers, winners)
        return getResults(winners)
    }

    private fun checkNumbers(inputNumbers: MutableList<List<String>>, drawnNumbers: List<String>, winners: MutableMap<Int, Int>) {
        inputNumbers.forEach { line ->
            line.count { drawnNumbers.contains(it) }
                .let { winners.merge(it, 1, Int::plus) }
        }
        logger.debug("For $drawnNumbers the winners are: $winners")
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

