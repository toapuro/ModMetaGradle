package io.github.toapuro.modmetagradle

import org.gradle.api.Project

abstract class ModMetaExtensionImpl : ModMetaExtension {

    companion object {
        const val NAME: String = "modMeta"

        fun create(project: Project): ModMetaExtension {
            var extensions = project.extensions
            return extensions.create(
                ModMetaExtension::class.java,
                NAME,
                ModMetaExtensionImpl::class.java
            )
        }
    }
}