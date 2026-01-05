@file:Suppress("unused")

package io.github.toapuro.modmetagradle.logic

import io.github.toapuro.modmetagradle.toml.ConfigName
import io.github.toapuro.modmetagradle.toml.Mandatory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

/**
 * Represents the mods.toml format
 * @see <a href="https://docs.minecraftforge.net/en/latest/gettingstarted/modfiles/">Forge</a>
 * @see <a href="https://docs.neoforged.net/docs/gettingstarted/modfiles/#neoforgemodstoml">Neoforge</a>
 */
data class MetaInfo(
    val fileMeta: FileMeta,
    val modMeta: ModMeta,
    val dependenciesMeta: List<DependencyMeta>
) {
    /**
     * @see <a href="https://docs.minecraftforge.net/en/latest/gettingstarted/modfiles/#non-mod-specific-properties">#non-mod-specific-properties</a>
     */
    abstract class FileMeta {


        /**
         * [Mandatory]
         *
         * Options: ["javafml", "lowcodefml"]
         *
         * Example: "javafml"
         */
        @get:Input @Mandatory abstract val modLoader: Property<String>

        /**
         * [Mandatory]
         *
         * Language loader version range
         *
         * @see <a href="https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html">Version Range Format</a>
         */
        @get:Input @Mandatory abstract val loaderVersion: Property<String>

        /**
         * [Mandatory]
         *
         * Example: "MIT"
         */
        @get:Input @Mandatory abstract val license: Property<String>

        /**
         * Default Value: false
         */
        @get:Input abstract val clientSideOnly: Property<Boolean>

        /**
         * Default Value: false
         */
        @get:Input abstract val showAsResourcePack: Property<Boolean>

        /**
         * Default Value: []
         */
        @get:Input abstract val services: ListProperty<String>

        /**
         * Default Value: {}
         */
        @get:Input abstract val properties: MapProperty<String, String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val issueTrackerURL: Property<String>
    }

    abstract class ModMeta {

        /**
         * [Mandatory]
         */
        @get:Input @Mandatory abstract val modId: Property<String>

        /**
         * Default Value: value of modId
         */
        @get:Input abstract val namespace: Property<String>

        /**
         * Default Value: 1.0
         */
        @get:Input abstract val version: Property<String>

        /**
         * Default Value: value of modId
         */
        @get:Input abstract val displayName: Property<String>

        /**
         * Default Value: "MISSING DESCRIPTION"
         */
        @get:Input abstract val description: Property<String>

        /**
         * Default Value: nothing,
         */
        @get:Input abstract val logoFile: Property<String>

        /**
         * Default Value: true
         */
        @get:Input abstract val logoBlur: Property<Boolean>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val updateJSONURL: Property<String>

        /**
         * Default Value: {}
         */
        @get:Input abstract val features: MapProperty<String, String>

        /**
         * Default value {}
         */
        @ConfigName("modproperties")
        @get:Input abstract val modProperties: MapProperty<String, String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val modUrl: Property<String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val credits: Property<String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val authors: Property<String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val displayURL: Property<String>

        /**
         * Default Value: "MATCH_VERSION"
         */
        @get:Input abstract val displayTest: Property<String>
    }

    abstract class DependencyMeta {

        /**
         * [Mandatory]
         */
        @get:Input @Mandatory abstract val id: Property<String>

        /**
         * [Mandatory]
         */
        @get:Input @Mandatory abstract val mandatory: Property<Boolean>

        /**
         * Default Value: ""
         *
         * Mod version range
         *
         * @see <a href="https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html">Version Range Format</a>
         */
        @get:Input abstract val versionRange: Property<String>

        /**
         * Default Value: "NONE"
         */
        @get:Input abstract val ordering: Property<String>

        /**
         * Default Value: "BOTH"
         */
        @get:Input abstract val side: Property<String>

        /**
         * Default Value: nothing
         */
        @get:Input abstract val referralUrl: Property<String>
    }
}