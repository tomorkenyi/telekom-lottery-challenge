package application

import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import service.LotteryService
import service.NumberReaderService
import java.io.IOException
import javax.inject.Inject

@QuarkusMain
class LotteryApplication(
    @Inject
    private val numberReaderService: NumberReaderService,
    @Inject
    private val lotteryService: LotteryService,
) : QuarkusApplication {

    override fun run(vararg args: String?): Int {
        val numbers = numberReaderService.loadNumbers(args[0] ?: throw IOException("Please provide a file."))
        println("READY")

        while (true) {
            lotteryService.startLottery(numbers)
        }
    }
}
