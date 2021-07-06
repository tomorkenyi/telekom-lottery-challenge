package util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import util.ValidatorUtil.Companion.validate

class ValidatorUtilTest {

    @Test
    fun testValidation_less_numbers() {
        assertFalse { validate("1 2 3 4") }
    }

    @Test
    fun testValidation_more_numbers() {
        assertFalse { validate("16 12 32 46 53 66") }
    }

    @Test
    fun testValidation_duplicate_numbers() {
        assertFalse { validate("17 2 23 45 45") }
    }

    @Test
    fun testValidation_zero_number() {
        assertFalse { validate("71 26 34 54 0") }
    }

    @Test
    fun testValidation_invalid_number_more() {
        assertFalse { validate("16 22 33 41 99") }
    }

    @Test
    fun testValidation_invalid_number_negative() {
        assertFalse { validate("-12 23 36 49 55") }
    }

}
