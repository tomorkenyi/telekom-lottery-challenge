import java.io.File

class TestUtil {

    companion object {
        fun createFile(): File {
            val file = File(TestConstants.fileName)

            return when {
                file.createNewFile() -> file
                else -> recreateFile(file)
            }
        }

        private fun recreateFile(file: File): File {
            deleteFile()
            file.createNewFile()
            return file
        }

        fun deleteFile() {
            when {
                File(TestConstants.fileName).exists() -> File(TestConstants.fileName).delete()
            }
        }
    }



}
