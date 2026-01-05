package io.github.toapuro.modmetagradle.toml

import com.electronwill.nightconfig.core.Config
import groovy.json.JsonException
import io.github.toapuro.modmetagradle.ext.nullIfEmpty
import io.github.toapuro.modmetagradle.toml.ObjectSerializer.TRANSFORMS
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotations
import kotlin.reflect.full.memberProperties

/**
 * A class that serializes class properties
 *
 * @property TRANSFORMS Transformers that convert values for handling in Config
 */
object ObjectSerializer {
    private val TRANSFORMS = mutableMapOf<KClass<*>, (Any?) -> Any?>()

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> serialize(valueKlass: KClass<in T>, data: T, config: Config) {
        valueKlass.memberProperties
            .filter { field -> field.findAnnotations(HideFromConfig::class).isEmpty() }
            .forEach { field ->
                var name = field.name
                field.findAnnotations(ConfigName::class).firstOrNull()?.let {
                    name = it.name
                }
                val isRequired = !field.findAnnotations(Mandatory::class).isEmpty()

                var rawValue: Any? = (field as KProperty1<Any, *>).get(data)

                // Convert
                for (conversion in TRANSFORMS) {
                    if(conversion.key.isInstance(rawValue)) {
                        rawValue = conversion.value.invoke(rawValue)
                    }
                }

                if(rawValue != null) {
                    config.set<Any>(name, rawValue)
                } else if(isRequired) {
                    throw JsonException("Property \"${name}\" is required ${field.annotations}")
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> registerTransformer(klass: KClass<T>, converter: (T?) -> Any?) {
        TRANSFORMS.put(klass, { converter.invoke(it as T?) })
    }

    init {
        registerTransformer(Property::class) { it?.orNull }
        registerTransformer(MapProperty::class) { it?.get()?.nullIfEmpty()}
        registerTransformer(ListProperty::class) { it?.get()?.nullIfEmpty() }
        registerTransformer(Map::class) {
            it?.let {
                Config.inMemory().apply {
                    it.forEach { set(it.key.toString(), it.value) }
                }
            }
        }
    }
}