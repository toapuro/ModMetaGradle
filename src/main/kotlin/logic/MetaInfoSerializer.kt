package io.github.toapuro.modmetagradle.logic

import com.electronwill.nightconfig.core.Config
import io.github.toapuro.modmetagradle.ext.setValueList
import io.github.toapuro.modmetagradle.toml.ObjectSerializer

object MetaInfoSerializer {

    fun serialize(config: Config, metaInfo: MetaInfo) {
        ObjectSerializer.serialize(MetaInfo.FileMeta::class, metaInfo.fileMeta, config)

        // [[mods]]
        val modConfig = Config.inMemory()
        config.setValueList(listOf("mods"), listOf(modConfig))

        ObjectSerializer.serialize(MetaInfo.ModMeta::class, metaInfo.modMeta, modConfig)

        val dependencyList = mutableListOf<Config>()
        for (meta in metaInfo.dependenciesMeta) {
            val dependencyConfig = Config.inMemory()
            ObjectSerializer.serialize(MetaInfo.DependencyMeta::class, meta, dependencyConfig)

            dependencyList.add(dependencyConfig)
        }

        // [[dependencies.${modId}]]
        config.setValueList(listOf("dependencies.${metaInfo.modMeta.modId.get()}"), dependencyList)
    }
}