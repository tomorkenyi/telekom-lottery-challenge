package util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidatorUtilTest {

    @Test
    fun testValidation_less_numbers() {
        assertFalse { ValidatorUtil.validate("1 2 3 4") }
    }

    @Test
    fun testValidation_more_numbers() {
        assertFalse { ValidatorUtil.validate("16 12 32 46 53 66") }
    }

    @Test
    fun testValidation_duplicate_numbers() {
        assertThrows<NoSuchElementException> { ValidatorUtil.validate("17 2 23 45 45") }
    }

    @Test
    fun testValidation_zero_number() {
        assertFalse { ValidatorUtil.validate("71 26 34 54 0") }
    }

    @Test
    fun testValidation_invalid_number_more() {
        assertFalse { ValidatorUtil.validate("16 22 33 41 99") }
    }

    @Test
    fun testValidation_invalid_number_negative() {
        assertFalse { ValidatorUtil.validate("-12 23 36 49 55") }
    }

}
