package service

import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class LotteryService(
    @Inject
    private val drawnNumberReaderService: DrawnNumberReaderService,
    @Inject
    private val winnerEvaluatorService: WinnerEvaluatorService
) {

    private val logger = Logger.getLogger(javaClass)

    fun startLottery(inputNumbers: MutableList<String>): String {
        val drawnNumbersList = drawnNumberReaderService.readDrawnNumbers()
        logger.debug("Drawn numbers: $drawnNumbersList")
        parallelSearch(drawnNumbersList, inputNumbers)
        return "0 0 0 0"
    }

    private fun parallelSearch(drawnNumbersList: MutableList<List<String>>, inputNumbers: MutableList<String>) {
        drawnNumbersList.stream().parallel().forEach {
            logger.debug("Searching...")
            winnerEvaluatorService.searchWinners(inputNumbers, it)
        }
    }
}
