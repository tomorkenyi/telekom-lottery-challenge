package service

import util.ValidatorUtil.Companion.validate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    fun readDrawnNumbers(): List<String> {

        val line = readLine() ?: throw IllegalArgumentException("No input is given.")

        try {
            return when (validate(line)) {
                true -> line.split(" ")
                else -> throw NoSuchElementException()
            }
        } catch (e: NoSuchElementException) {
            System.err.println("Input '$line' is invalid. Please provide 5 distinct lottery numbers separated by space.")
        }
        return readDrawnNumbers()
    }
}
