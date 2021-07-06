package service

import org.jboss.logging.Logger
import java.io.BufferedReader
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class NumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    var myList = mutableListOf<String>()

    fun loadNumbers(fileName: String): MutableList<String> {
        logger.debug("Reading file: $fileName")
        val bufferedReader = File(fileName).bufferedReader()
        bufferedReader.forEachLine { myList.add(it) }
        return myList
    }
}
