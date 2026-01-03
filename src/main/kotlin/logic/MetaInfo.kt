package io.github.toapuro.modmetagradle.logic

import io.github.toapuro.modmetagradle.toml.ConfigName
import io.github.toapuro.modmetagradle.toml.OptionalConfig
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

data class MetaInfo(
    val fileMeta: FileMeta,
    val modMeta: ModMeta,
    val dependenciesMeta: List<DependencyMeta>
) {
    abstract class FileMeta {
        abstract val modLoader: Property<String>
        abstract val loaderVersion: Property<String>
        abstract val license: Property<String>
        @OptionalConfig abstract val clientSideOnly: Property<Boolean> // Default false
        @OptionalConfig abstract val showAsResourcePack: Property<Boolean> // Default false
        @OptionalConfig abstract val services: ListProperty<String> // Default []
        @OptionalConfig abstract val properties: MapProperty<String, String> // Default {}
        @OptionalConfig abstract val issueTrackerURL: Property<String> // Default nothing
    }

    abstract class ModMeta {
        abstract val modId: Property<String>
        @OptionalConfig abstract val namespace: Property<String> // Default value of modId
        @OptionalConfig abstract val version: Property<String> // Default 1.0
        @OptionalConfig abstract val displayName: Property<String> // Default value of modId
        @OptionalConfig abstract val description: Property<String> // Default "MISSING DESCRIPTION"
        @OptionalConfig abstract val logoFile: Property<String> // Default nothing,
        @OptionalConfig abstract val logoBlur: Property<Boolean> // Default true
        @OptionalConfig abstract val updateJSONURL: Property<String> // Default nothing
        @OptionalConfig abstract val features: MapProperty<String, String> // Default {}
        @ConfigName("modproperties")
        @OptionalConfig abstract val modProperties: MapProperty<String, String> // Default {}
        @OptionalConfig abstract val modUrl: Property<String> // Default nothing
        @OptionalConfig abstract val credits: Property<String> // Default nothing
        @OptionalConfig abstract val authors: Property<String> // Default nothing
        @OptionalConfig abstract val displayURL: Property<String> // Default nothing
        @OptionalConfig abstract val displayTest: Property<String> // Default "MATCH_VERSION"
    }

    abstract class DependencyMeta {
        abstract val id: Property<String>
        abstract val mandatory: Property<Boolean>
        @OptionalConfig abstract val versionRange: Property<String> // Default ""
        @OptionalConfig abstract val ordering: Property<String> // Default "NONE"
        @OptionalConfig abstract val side: Property<String> // Default "BOTH"
        @OptionalConfig abstract val referralUrl: Property<String> // Default nothing
    }
}