package service

import TestConstants.fileName
import TestConstants.one_is_illegal
import TestConstants.sample
import TestUtil.Companion.createFile
import TestUtil.Companion.deleteFile
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.BufferedReader
import java.io.File
import javax.enterprise.inject.Default
import javax.inject.Inject

private val drawnNumberReaderService: DrawnNumberReaderService = mockk()
private val numberReaderService: NumberReaderService = spyk()

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LotteryServiceTest {

    @Inject
    @field: Default
    lateinit var winnerEvaluatorService: WinnerEvaluatorService

    lateinit var file: File

    @BeforeEach
    internal fun setUp() {
        file = createFile()
    }

    @Test
    fun testSearchWinners_one_2_one_3_one_4_three_5() {
        file.writeText(sample)
        assertEquals("1 1 1 3", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("1", "2", "3", "4", "5")))
    }

    @Test
    fun testSearchWinners_two_2() {
        file.writeText(sample)
        assertEquals("0 1 0 0", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("31", "40", "35", "90", "89")))
    }

    @Test
    fun testSearchWinners_one_3() {
        file.writeText(sample)
        assertEquals("2 0 0 0", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("31", "22", "77", "90", "89")))
    }

    @Test
    fun testSearchWinners_three_4() {
        file.writeText(sample)
        assertEquals("0 0 3 0", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("66", "33", "77", "55", "1")))
    }

    @Test
    fun testSearchWinners_one_2_one_3_one_4_two_5_one_illegal() {
        file.writeText(one_is_illegal)
        assertEquals("1 1 1 2", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("1", "2", "3", "4", "5")))
    }

    @Test
    fun testSearchWinners_zero_results() {
        file.writeText(sample)
        assertEquals("0 0 0 0", winnerEvaluatorService.searchWinners(numberReaderService.loadNumbers(fileName), listOf("11", "12", "13", "14", "15")))
    }

    @AfterEach
    internal fun tearDown() {
        deleteFile()
    }

    companion object {
        @BeforeAll
        fun setupMocks() {
            QuarkusMock.installMockForType(drawnNumberReaderService, DrawnNumberReaderService::class.java)
        }
    }
}
