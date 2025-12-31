package io.github.toapuro.modmetagradle.toml

import com.electronwill.nightconfig.core.Config
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotations
import kotlin.reflect.full.memberProperties

object ObjectSerializer {

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> serialize(value: T, config: Config) {
        value::class.memberProperties
            .filter { field -> field.findAnnotations(HideFromConfig::class).isEmpty() }
            .forEach { field ->
                var name = field.name
                field.findAnnotations(ConfigName::class).firstOrNull()?.let {
                    name = it.name
                }

                val fieldValue: Any? = (field as KProperty1<Any, *>).get(value)
                if(fieldValue != null) {
                    config.set<Any>(name, fieldValue)
                }
            }
    }
}