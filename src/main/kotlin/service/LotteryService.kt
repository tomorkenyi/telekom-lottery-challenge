package service

import org.jboss.logging.Logger
import java.util.concurrent.CompletableFuture
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

        // Quarkus doesn't support coroutines or flows from Kotlin yet
        CompletableFuture.supplyAsync {
            logger.debug("Started searching for the winners of $drawnNumbersList")
            winnerEvaluatorService.searchWinners(inputNumbers, drawnNumbersList)
            logger.debug("Search finished for $drawnNumbersList")
        }

    }
}
