package service

import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File
import javax.enterprise.inject.Default
import javax.inject.Inject

private val drawnNumberReaderService: DrawnNumberReaderService = mockk()

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LotteryServiceTest {

    @BeforeAll
    fun setupMocks() {
        QuarkusMock.installMockForType(drawnNumberReaderService, DrawnNumberReaderService::class.java)
    }

    @Inject
    @field: Default
    lateinit var lotteryService: LotteryService

    @Inject
    @field: Default
    lateinit var numberReaderService: NumberReaderService

    @BeforeEach
    internal fun setUp() {
        createFile("input.txt")
    }

    @AfterEach
    internal fun tearDown() {
        deleteFile("input.txt")
    }

    @Test
    fun startLottery() {
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("1", "2", "3", "4", "5")
        assertEquals("1 1 1 3", lotteryService.startLottery(numberReaderService.loadNumbers("input.txt")))
    }

    fun createFile(fileName: String): File {
        val file = File(fileName)

        when {
            file.createNewFile() -> {
                file.writeText(
                    "1 2 3 4 5\n" +
                            "5 3 4 2 1\n" +
                            "5 4 3 2 1\n" +
                            "31 22 44 45 5\n" +
                            "16 5 81 3 19\n" +
                            "23 5 4 3 19\n" +
                            "87 2 4 3 5\n" +
                            "31 22 44 45 9\n"
                )
                println("$fileName is created successfully.")
            }
            else -> println("$fileName already exists.")
        }
        return file
    }

    fun deleteFile(fileName: String) {
        val file = File(fileName)
        if (file.exists()) {
            file.delete()
        }
    }
}
