package service

import util.ValidatorUtil.Companion.validate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    fun readDrawnNumbers(): List<String> {

        val line = readLine() ?: throw IllegalArgumentException("No input is given.")

        return when (validate(line)) {
            true -> line.split(" ")
            else -> {
                System.err.println("Please provide 5 distinct lottery numbers separated by space: $line")
                emptyList()
            }
        }
    }
}
