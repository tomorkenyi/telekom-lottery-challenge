package service

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DrawnNumberReaderService {

    fun readDrawnNumbers(): List<String>? {
        println("Please provide the drawn numbers.")
        // TODO error handling (format, scope)
        return readLine()?.split(" ")
    }
}
