package helpers

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

/**
 * Contain utils that can be reused across all projects
 */
object Util {

    fun getVersionPublished(): String {
        return try {
            val versionFile = File("version.properties")
            val properties = Properties().apply {
                load(FileInputStream(versionFile))
            }
            properties.getProperty("VERSION").toString()
        } catch (e: Exception) {
            "1.0.0-local"
        }
    }

    fun File.getPropertiesFromFilename(fileName: String): Properties {
        val properties = Properties()
        val fileProperties = File(this, fileName)
        if (fileProperties.isFile) {
            InputStreamReader(FileInputStream(fileProperties)).use { reader ->
                properties.load(reader)
            }
        } else {
            println("The file $fileName is not found at $this")
        }
        return  properties
    }
}
