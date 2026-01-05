package io.github.toapuro.modmetagradle

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory

abstract class ModMetaExtensionImpl(val project: Project, val objects: ObjectFactory) : ModMetaExtension {

    override fun autoForgeOutput() {
        outputFile.set(objects.fileProperty().also {
            it.set(project.layout.buildDirectory.file("resources/main/META-INF/mods.toml"))
        })
    }

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