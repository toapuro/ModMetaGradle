package io.github.toapuro.modmetagradle.task

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import io.github.toapuro.modmetagradle.ModMetaExtension
import io.github.toapuro.modmetagradle.logic.MetaInfo
import io.github.toapuro.modmetagradle.logic.MetaInfoSerializer
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

abstract class GenerateModMetaTask: DefaultTask() {

    @get:Internal
    lateinit var extensionRef: ModMetaExtension

    @TaskAction
    fun run() {
        val outputFile = extensionRef.outputFile.get().asFile

        val fileMeta = extensionRef.file
        val modMeta = extensionRef.mod
        val dependencies = extensionRef.dependencies

        val metaInfo = MetaInfo(fileMeta, modMeta, dependencies.all())

        val config = CommentedFileConfig
            .builder(outputFile)
            .build()

        MetaInfoSerializer.serialize(config, metaInfo)

        config.save()
    }

    companion object {
        const val NAME = "generateModMeta"

        fun create(project: Project, action: Action<GenerateModMetaTask>) {
            val tasks = project.tasks
            tasks.register(NAME, GenerateModMetaTask::class.java) {
                action.execute(this)
            }
        }
    }
}