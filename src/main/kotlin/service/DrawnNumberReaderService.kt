package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun readDrawnNumbers(): MutableList<List<String>> {
        logger.debug("Start asking for input drawn numbers.")

        val drawnNumberList = mutableListOf<List<String>>()

        generateSequence(::readLine)
            .takeWhile { !it.matches("".toRegex()) }
            .forEach { line ->
                try {
                    when {
                        validate(line) -> drawnNumberList.add(line.split(" "))
                        else -> throw NoSuchElementException("Pattern is not right for this line: $line")
                    }
                } catch (e: Exception) {
                    System.err.println("Please provide 5 distinct lottery numbers separated by space, cause: ${e.message}")
                }
            }

        return drawnNumberList
    }
}
