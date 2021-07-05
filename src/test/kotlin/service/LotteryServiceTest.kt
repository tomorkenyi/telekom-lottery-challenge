package service

import TestConstants.fileName
import TestConstants.one_is_illegal
import TestConstants.sample
import TestUtil.Companion.createFile
import TestUtil.Companion.deleteFile
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

    lateinit var file: File

    @BeforeEach
    internal fun setUp() {
        file = createFile()
    }

    @Test
    fun testStartLottery_one_2_one_3_one_4_three_5() {
        file.writeText(sample)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("1", "2", "3", "4", "5")
        assertEquals("1 1 1 3", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @Test
    fun testStartLottery_two_2() {
        file.writeText(sample)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("31", "40", "35", "90", "89")
        assertEquals("0 1 0 0", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @Test
    fun testStartLottery_one_3() {
        file.writeText(sample)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("31", "22", "77", "90", "89")
        assertEquals("2 0 0 0", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @Test
    fun testStartLottery_three_4() {
        file.writeText(sample)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("66", "33", "77", "55", "1")
        assertEquals("0 0 3 0", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @Test
    fun testStartLottery_one_2_one_3_one_4_two_5_one_illegal() {
        file.writeText(one_is_illegal)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("1", "2", "3", "4", "5")
        assertEquals("1 1 1 2", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @Test
    fun testStartLottery_zero_results() {
        file.writeText(sample)
        every { drawnNumberReaderService.readDrawnNumbers() } returns listOf("11", "12", "13", "14", "15")
        assertEquals("0 0 0 0", lotteryService.startLottery(numberReaderService.loadNumbers(fileName)))
    }

    @AfterEach
    internal fun tearDown() {
        deleteFile()
    }
}
