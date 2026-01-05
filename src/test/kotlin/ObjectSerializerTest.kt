
import com.electronwill.nightconfig.core.Config
import com.electronwill.nightconfig.toml.TomlWriter
import io.github.toapuro.modmetagradle.toml.ObjectSerializer
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.testfixtures.ProjectBuilder
import java.io.PrintWriter
import kotlin.test.Test

inline fun <reified T> ObjectFactory.constProperty(value: T?): Property<T> =
    property(T::class.java).apply { set(value) }
inline fun <reified T> ObjectFactory.constListProperty(value: List<T>): ListProperty<T> =
    listProperty(T::class.java).apply { set(value) }
inline fun <reified K, reified V> ObjectFactory.constMapProperty(map: Map<K, V>): MapProperty<K, V> =
    mapProperty(K::class.java, V::class.java).apply { putAll(map) }


object ObjectSerializerTest {

    @Test
    fun serializeTest() {
        val project = ProjectBuilder.builder().build()
        val objects = project.objects

        val data = TestData(
            "HelloWorld",
            objects.constProperty("1.0"),
            objects.constListProperty(listOf("element1", "element2")),
            objects.constMapProperty(mapOf("ping" to "pong"))
        )

        val config = Config.inMemory()
        ObjectSerializer.serialize<TestData>(TestData::class, data, config)

        TomlWriter().write(config, PrintWriter(System.out))
    }

    data class TestData(
        val name: String,
        val version: Property<String>,
        val hoge: ListProperty<String>,
        val huga: MapProperty<String, String>
    )
}