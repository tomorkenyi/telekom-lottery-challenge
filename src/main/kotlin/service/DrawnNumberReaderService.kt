package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun readDrawnNumbers(): MutableList<List<String>> {
        logger.debug("Start asking for input drawn numbers.")

        val lines = generateSequence(::readLine)
        val assembledList = mutableListOf<List<String>>()

        lines.takeWhile { !it.matches("".toRegex()) }
            .forEach { line ->
                try {
                    if (validate(line)) {
                        assembledList.add(line.split(" "))
                    } else {
                        throw NoSuchElementException("Pattern is not right.")
                    }
                } catch (e: Exception) {
                    System.err.println("Please provide 5 distinct lottery numbers separated by space.")
                }
            }
        return assembledList
    }
}
