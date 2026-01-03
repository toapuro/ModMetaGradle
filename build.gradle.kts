plugins {
    kotlin("jvm") version "2.1.10"
    groovy
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "io.github.toapuro.modmetagradle"
version = "1.0"
description = "Generate mods.toml"

println("Version: $version")

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)

    withSourcesJar()
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(gradleTestKit())

    implementation("com.electronwill.night-config:toml:3.6.0")
}

gradlePlugin {
    plugins.register("modmetagradle") {
        id = "modmetagradle"
        implementationClass = "io.github.toapuro.modmetagradle.ModMetaPlugin"
        displayName = "ModMetaGradle"
        description = project.description
    }
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}