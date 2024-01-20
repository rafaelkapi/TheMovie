package projects.library

import projects.model.AndroidVersion
import projects.model.DefaultModuleNames
import projects.model.Module
import projects.model.ModuleType
import projects.model.ProjectInfo

object Commons {
    val projectInfo = ProjectInfo(
        name = Modules.ROOT,
        applicationId = "com.cactus.android.sample",
        groupId = "com.cactus.commons",
        scheme = "appthemovie",
        
        modules = listOf(
            Module(
                path = Modules.sample,
                type = ModuleType.ANDROID_SAMPLE,
            )
        )
    )

    object Modules {
        const val ROOT = ":commons"
        const val sample = "$ROOT:${DefaultModuleNames.SAMPLE}"
    }
}