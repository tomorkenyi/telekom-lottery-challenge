package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
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
}
