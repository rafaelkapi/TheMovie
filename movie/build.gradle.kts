import helpers.applyCustomPluginsByModuleType
import core.setupAndroidExtensions
import projects.library.Movie

subprojects {
    this.applyCustomPluginsByModuleType(Movie.projectInfo)
    this.setupAndroidExtensions()
}