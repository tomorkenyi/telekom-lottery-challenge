package service

import org.jboss.logging.Logger
import util.ValidatorUtil.Companion.validate
import java.io.BufferedReader
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class NumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun loadNumbers(fileName: String): MutableList<List<String>> {
        logger.debug("Reading file: $fileName")
        return copyToList(File(fileName).bufferedReader())
    }

    private fun copyToList(bufferedReader: BufferedReader): MutableList<List<String>> {
        val inputNumbers = mutableListOf<List<String>>()
        bufferedReader.forEachLine {
            when {
                validate(it) -> inputNumbers += it.split(" ")
                else -> logger.debug("Has duplicates or not lottery numbers in the required format: $it will be skipped")
            }
        }
        logger.debug("Reading input file finished.")
        return inputNumbers
    }
}
