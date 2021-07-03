package application

import service.LotteryService
import service.NumberReaderService
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
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
        while (true) {
            val numbers = numberReaderService.loadNumbers(args[0] ?: throw IOException("Please provide a file."))
            println("READY")
            lotteryService.startLottery(numbers)
        }
    }
}
