package io.github.toapuro.modmetagradle

import org.gradle.api.file.RegularFileProperty

interface ModMetaExtension {
    val outputFile: RegularFileProperty
}