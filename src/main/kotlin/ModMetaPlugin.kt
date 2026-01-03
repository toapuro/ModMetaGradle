package io.github.toapuro.modmetagradle

import io.github.toapuro.modmetagradle.task.GenerateModMetaTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.language.jvm.tasks.ProcessResources

@Suppress("unused")
class ModMetaPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val ext = ModMetaExtensionImpl.create(target)
        GenerateModMetaTask.create(target) {
            this.extensionRef = ext
        }

        with(target) {
            tasks.withType(ProcessResources::class.java) {
                dependsOn(GenerateModMetaTask.NAME)
            }
        }
    }
}