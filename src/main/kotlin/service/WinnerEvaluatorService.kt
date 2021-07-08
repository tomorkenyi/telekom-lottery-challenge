package service

import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WinnerEvaluatorService {

    private val logger = Logger.getLogger(javaClass)

    fun searchWinners(inputNumbers: MutableList<List<String>>, drawnNumbers: List<String>): String {
        return getResults(checkNumbers(inputNumbers, drawnNumbers))
    }

    private fun checkNumbers(inputNumbers: MutableList<List<String>>, drawnNumbers: List<String>): MutableMap<Int, Int> {
        val winners = HashMap<Int, Int>()

        inputNumbers.groupingBy { line ->
            line.count { drawnNumbers.contains(it) }
        }.eachCountTo(winners)

        logger.debug("For $drawnNumbers the winners are: $winners")
        return winners
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

