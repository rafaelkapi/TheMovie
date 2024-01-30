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
                path = Modules.network,
                type = ModuleType.ANDROID_LIBRARY,
            ),
            Module(
                path = Modules.base,
                type = ModuleType.ANDROID_LIBRARY,
            ),
            Module(
                path = Modules.extensions,
                type = ModuleType.ANDROID_LIBRARY,
            )
        )
    )

    object Modules {
        const val ROOT = ":commons"
        val network = "$ROOT:network"
        val base = "$ROOT:base"
        val extensions = "$ROOT:extensions"
    }
}