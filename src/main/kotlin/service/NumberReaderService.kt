package service

import org.jboss.logging.Logger
import java.io.BufferedReader
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class NumberReaderService {

    private val logger = Logger.getLogger(javaClass)

    fun loadNumbers(fileName: String): BufferedReader {
        logger.debug("Reading file: $fileName")
        return File(fileName).bufferedReader()
    }
}
