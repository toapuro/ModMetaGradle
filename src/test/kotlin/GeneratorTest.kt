
import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.toml.TomlWriter
import io.github.toapuro.modmetagradle.logic.ModMeta
import io.github.toapuro.modmetagradle.logic.ModMetaSerializer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.PrintWriter

class GeneratorTest {

    @TempDir lateinit var testDir: File

    @Test
    fun testSerializer() {
        val modMeta = ModMeta(
            ModMeta.FileMeta(
                "testLoader",
                "1.0",
                "MIT"
            ),
            ModMeta.SpecificMeta(
                "example_mod"
            ),
            emptyList()
        )

        val tomlFile = File(testDir, "mods.toml")
        val config = CommentedFileConfig
            .builder(tomlFile)
            .build()

        ModMetaSerializer.serialize(config, modMeta)

        PrintWriter(System.out).use {
            TomlWriter().write(config, it)
        }
    }
}