package util

class ValidatorUtil {
    companion object {

        const val pattern = "^((90|[1-8][0-9]|[1-9])\\s){4}(90|[1-8][0-9]|[1-9])\$"

        fun validate(line: String): Boolean {
            return when {
                // TODO check with regex
                !hasDuplicates(line) -> {
                    line.matches(Regex(pattern))
                }
                else -> throw NoSuchElementException("Input has duplicates, lottery numbers should be distinct.")
            }
        }

        fun hasDuplicates(line: String): Boolean {
            val elements = line.splitToSequence(" ")
            return elements.count() != elements.distinct().count()
        }
    }
}
