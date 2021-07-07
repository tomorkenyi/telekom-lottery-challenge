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

    fun startLottery(inputNumbers: MutableList<List<String>>) {
        val drawnNumbersList = drawnNumberReaderService.readDrawnNumbers()
        logger.debug("Drawn numbers: $drawnNumbersList")
        parallelSearch(inputNumbers, drawnNumbersList)
    }

    private fun parallelSearch(inputNumbers: MutableList<List<String>>, drawnNumbersList: MutableList<List<String>>) {
        logger.info("Started searching for the winners...")
        drawnNumbersList.stream().parallel().forEach {
            winnerEvaluatorService.searchWinners(inputNumbers, it)
        }
        logger.info("Search finished.")
    }
}
