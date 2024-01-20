package core

import helpers.Util.getPropertiesFromFilename
import org.gradle.api.Project

const val VERSION_PROPERTY = "VERSION"
const val VERSION_FILE_PATH = "/version.properties"

/**
 * Get VERSION from version.properties file
 */
fun Project.getModuleVersion(): String {
    val versionProperties = rootDir.getPropertiesFromFilename(VERSION_FILE_PATH)
    return versionProperties.getProperty(VERSION_PROPERTY)
}
