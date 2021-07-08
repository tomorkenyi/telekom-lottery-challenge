package util

class ValidatorUtil {
    companion object {

        private val pattern = "^((90|[1-8][0-9]|[1-9])\\s){4}(90|[1-8][0-9]|[1-9])\$".toRegex()

        fun validate(line: String): Boolean {
            return when {
                pattern.matches(line) && !hasDuplicates(line) -> true
                else -> false
            }
        }

        private fun hasDuplicates(line: String): Boolean {
            return line.split(" ").groupingBy { it }.eachCount().any { it.value > 1 }
        }
    }
}
