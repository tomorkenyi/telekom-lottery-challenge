package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun readDrawnNumbers(): MutableList<List<String>> {
        val drawnNumberList = mutableListOf<List<String>>()

        generateSequence(::readLine)
            .takeWhile { !it.matches("".toRegex()) }
            .forEach { line ->
                when {
                    validate(line) -> drawnNumberList.add(line.split(" "))
                    else -> System.err.println("Please provide 5 distinct lottery numbers separated by space: $line")
                }
            }

        return drawnNumberList
    }
}
