package util

class ValidatorUtil {
    companion object {

        const val pattern = "^((90|[1-8][0-9]|[1-9])\\s){4}(90|[1-8][0-9]|[1-9])\$"

        fun validate(line: String): Boolean {
            return line.matches(Regex(pattern))
        }
    }
}
