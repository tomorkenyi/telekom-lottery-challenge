package service

import io.smallrye.mutiny.Uni
import kotlinx.coroutines.runBlocking
import org.jboss.logging.Logger
import java.time.Duration
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

        logger.info("Started searching for the winners...")
        winnerEvaluatorService.searchWinners(inputNumbers, drawnNumbersList)
        logger.info("Search finished.")
    }
}
