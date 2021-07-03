package service

import org.jboss.logging.Logger
import java.io.BufferedReader
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

    fun startLottery(numbers: BufferedReader) {
        val drawnNumbers = drawnNumberReaderService.readDrawnNumbers()

        logger.debug("Search started.")
        drawnNumbers?.let { winnerEvaluatorService.searchWinners(numbers, it) }
        logger.debug("Search complete.")
    }

}
