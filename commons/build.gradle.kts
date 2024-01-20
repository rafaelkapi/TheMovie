import helpers.applyCustomPluginsByModuleType
import projects.library.Commons
import core.setupAndroidExtensions

subprojects {
    this.applyCustomPluginsByModuleType(Commons.projectInfo)
    this.setupAndroidExtensions()
}