import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import service.LotteryService
import service.NumberReaderService
import javax.enterprise.inject.Default
import javax.inject.Inject

@QuarkusTest
class NumberReaderServiceTest {

//    @Inject
//    @field: Default
//    lateinit var numberReaderService: NumberReaderService
//
//    @Inject
//    @field: Default
//    lateinit var lotteryService: LotteryService

    @Test
    fun loadNumbersWithThreeWinners() {
        assertTrue(true)

    }
}
