import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.tasks.register("checkEmptyFiles") {
            doLast {
                println("\n=== CHECKING EMPTY FILES ===")

                val srcDir = project.file("src")
                if (!srcDir.exists()) {
                    println("src folder not found")
                    return@doLast
                }

                var emptyCount = 0
                srcDir.walkTopDown().forEach { file ->
                    if (file.isFile && file.length() == 0L) {
                        println("Empty file: ${file.relativeTo(project.projectDir)}")
                        emptyCount++
                    }
                }

                println("Total empty files:"+ emptyCount)
            }
        }

        project.tasks.register("countLines") {
            doLast {
                println("\n=== COUNTING LINES OF CODE ===")

                val srcDir = project.file("src")
                if (!srcDir.exists()) {
                    println("src folder not found")
                    return@doLast
                }

                var totalLines = 0

                srcDir.walkTopDown().forEach { file ->
                    if (file.isFile && file.extension == "java") {
                        val lines = file.readLines().size
                        totalLines += lines
                        println("${file.name}: $lines lines")
                    }
                }

                println("-----------------------------")
                println("Total lines: $totalLines")
            }
        }
    }
}