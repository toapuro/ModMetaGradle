package io.github.toapuro.modmetagradle.ext

fun <T> Collection<T>.nullIfEmpty(): Collection<T>? = takeIf { it.isNotEmpty() }

fun <K, V> Map<K, V>.nullIfEmpty(): Map<K, V>? = takeIf { it.isNotEmpty() }
