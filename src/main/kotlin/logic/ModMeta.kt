package io.github.toapuro.modmetagradle.logic

data class ModMeta(
    val fileMeta: FileMeta,
    val specificMeta: SpecificMeta,
    val dependenciesMeta: List<DependencyMeta>
) {
    data class FileMeta(
        val modLoader: String,
        val loaderVersion: String,
        val license: String,
        val clientSideOnly: Boolean? = null, // Default false
        val showAsResourcePack: Boolean? = null, // Default false
        val services: List<String>? = null, // Default []
        val properties: Map<String, String>? = null, // Default {}
        val issueTrackerURL: String? = null, // Default nothing
    )

    data class SpecificMeta(
        val modId: String,
        val namespace: String? = null, // Default value of modId
        val version: String? = null, // Default 1.0
        val displayName: String? = null, // Default value of modId
        val description: String? = null, // Default "MISSING DESCRIPTION"
        val logoFile: String? = null, // Default nothing,
        val logoBlur: Boolean? = null, // Default true
        val updateJSONURL: String? = null, // Default nothing
        val features: Map<String, String>? = null, // Default {}
        val modproperties: Map<String, String>? = null, // Default {}
        val modUrl: String? = null, // Default nothing
        val credits: String? = null, // Default nothing
        val authors: String? = null, // Default nothing
        val displayURL: String? = null, // Default nothing
        val displayTest: String? = null, // Default "MATCH_VERSION"
    )

    data class DependencyMeta(
        val id: String,
        val mandatory: Boolean,
        val versionRange: String? = null, // Default ""
        val ordering: String? = null, // Default "NONE"
        val side: String? = null, // Default "BOTH"
        val referralUrl: String? = null // Default nothing
    )
}