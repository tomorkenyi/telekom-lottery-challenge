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

    fun startLottery(numbers: BufferedReader): String {
        drawnNumberReaderService
            .readDrawnNumbers()!!
            .let {
                logger.debug("Searching...")
                return winnerEvaluatorService.searchWinners(numbers, it)
            }
    }

}
