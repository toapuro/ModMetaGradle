@file:Suppress("unused")

package io.github.toapuro.modmetagradle.logic

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance

/**
 * Represents the dependencies block
 */
abstract class Dependencies(private val objects: ObjectFactory) {

    private val list = mutableListOf<MetaInfo.DependencyMeta>()

    fun mod(modId: String, mandatory: Boolean = true, configure: Action<MetaInfo.DependencyMeta>) {
        val dependency = objects.newInstance(MetaInfo.DependencyMeta::class)
        dependency.id.set(modId)
        dependency.mandatory.set(mandatory)

        configure.execute(dependency)
        list.add(dependency)
    }

    fun all(): List<MetaInfo.DependencyMeta> = list
}