
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

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
            
            file {
                modLoader = "testLoader"
                loaderVersion = "1.0"
                license = "MIT"
            }
            
            mod {
                modId = "examplemod"
            }
        }
        """.trimIndent()
        buildFile.writeText(buildFileContent)

        val taskName = "generateModMeta"
        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments(taskName)
            .build()


        val task = result.task(":${taskName}")
        assertEquals(TaskOutcome.SUCCESS, task?.outcome)
    }
}