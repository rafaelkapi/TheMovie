package projects.app

import projects.model.AndroidVersion
import projects.model.DefaultModuleNames
import projects.model.Module
import projects.model.ModuleType
import projects.model.ProjectInfo

object MovieApp {
    val projectInfo = ProjectInfo(
        name = Modules.ROOT,
        applicationId = "com.cactus.movieApp",
        groupId = "com.cactus.movieApp",
        scheme = "themovieapp",
        versions = AndroidVersion(
            compileSdkVersion = "android-34",
            minSdkVersion = 24,
            targetSdk = 34
        ),
        modules = listOf(
            Module(path = Modules.ROOT, type = ModuleType.ANDROID_APPLICATION)
        )
    )

    object Modules {
        const val ROOT = ":app"
    }
}