plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("myPlugin") {
            id = "MyPlugin"
            implementationClass = "MyPlugin"
        }
    }
}