@file:Suppress("unused")

package io.github.toapuro.modmetagradle

import io.github.toapuro.modmetagradle.logic.Dependencies
import io.github.toapuro.modmetagradle.logic.MetaInfo
import org.gradle.api.Action
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Nested

/**
 * @see ModMetaExtensionImpl
 */
interface ModMetaExtension {
    val outputFile: RegularFileProperty

    @get:Nested
    val file: MetaInfo.FileMeta

    @get:Nested
    val mod: MetaInfo.ModMeta

    @get:Nested
    val dependencies: Dependencies

    fun file(action: Action<in MetaInfo.FileMeta>) = action.execute(file)

    fun mod(action: Action<in MetaInfo.ModMeta>) = action.execute(mod)

    fun dependencies(action: Action<in Dependencies>) {
        action.execute(dependencies)
    }

    fun autoBuildOutput()
}