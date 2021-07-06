package service

import org.jboss.logging.Logger
import java.io.BufferedReader
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class NumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun loadNumbers(fileName: String): MutableList<String> {
        logger.debug("Reading file: $fileName")
        return copyToList(File(fileName).bufferedReader())
    }

    private fun copyToList(bufferedReader: BufferedReader): MutableList<String> {
        val inputNumbers = mutableListOf<String>()
        bufferedReader.forEachLine(inputNumbers::add)
        return inputNumbers
    }
}
