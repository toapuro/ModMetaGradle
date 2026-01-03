package io.github.toapuro.modmetagradle.ext

import com.electronwill.nightconfig.core.Config


fun Config.setValue(path: String, value: Any): Unit = set<Unit>(path, value)

fun Config.setValueList(path: List<String>, value: Any): Unit = set<Unit>(path, value)