package io.github.toapuro.modmetagradle

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class ModMetaPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = ModMetaExtensionImpl.create(target)


    }
}