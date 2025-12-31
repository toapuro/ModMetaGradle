package io.github.toapuro.modmetagradle.logic

import com.electronwill.nightconfig.core.Config
import io.github.toapuro.modmetagradle.toml.ObjectSerializer

object ModMetaSerializer {

    fun serialize(config: Config, modMeta: ModMeta) {
        ObjectSerializer.serialize(modMeta.fileMeta, config)

        // [[mods]]
        val modConfig = Config.inMemory()
        config.set<Config>(listOf("mods"), modConfig)

        ObjectSerializer.serialize(modMeta.specificMeta, modConfig)

        for (meta in modMeta.dependenciesMeta) {
            // [[dependencies.${modMeta.specificMeta.modId}]]
            val dependencyConfig = Config.inMemory()
            config.set<Config>(listOf("dependencies.${modMeta.specificMeta.modId}"), dependencyConfig)

            ObjectSerializer.serialize(meta, dependencyConfig)
        }
    }
}