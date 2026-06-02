plugins {
    id("java")
    id("MyPlugin")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.register("countAllFiles") {
    doLast {
        val projectDir = project.projectDir

        val count = projectDir.walkTopDown()
            .filter { it.isFile && it.extension == "java" }
            .count()

        println("Total Java files in project: $count")
    }
}

tasks.register("checkAll") {
    dependsOn("checkEmptyFiles", "countLines")
}

tasks.test {
    useJUnitPlatform()
}