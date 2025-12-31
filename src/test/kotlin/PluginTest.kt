
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class PluginTest {
    @TempDir lateinit var testProjectDir: File

    private lateinit var settingsFile: File
    private lateinit var buildFile: File

    @BeforeEach
    fun setup() {
        settingsFile = File(testProjectDir, "settings.gradle.kts")
        buildFile = File(testProjectDir, "build.gradle.kts")
    }

    @Test
    fun testTask() {
        settingsFile.writeText("rootProject.name = \"plugin-test\"")
        val buildFileContent = """
        plugins {
            id("modmetagradle")    
        }
        modMeta {
            outputFile = file("mods.toml")
        }
        """.trimIndent()
        buildFile.writeText(buildFileContent)

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("build")
            .build()

        val output = result.output
        assert("BUILD SUCCESSFUL" in output)
    }
}