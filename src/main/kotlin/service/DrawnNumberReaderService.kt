package service

import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun readDrawnNumbers(): List<String>? {
        logger.debug("Start asking for input drawn numbers.")

        val line = readLine() ?: throw IllegalArgumentException("No input is given.")

        try {
            return when (validate(line)) {
                true -> line.split(" ")
                else -> throw NoSuchElementException("Pattern is not right.")
            }
        } catch (e: Exception) {
            System.err.println("Please provide 5 valid lottery numbers separated by space.")
        }
        return readDrawnNumbers()
    }

    fun validate(line: String): Boolean {
        return line.matches(Regex("^((90|[1-8][0-9]|[1-9])\\s){4}(90|[1-8][0-9]|[1-9])\$"))
    }
}
