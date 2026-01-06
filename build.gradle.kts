plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
    test {
        kotlin.srcDir("test")
    }
}

tasks.withType<JavaExec> {
    isIgnoreExitValue = true
    workingDir = rootProject.projectDir
}

tasks.withType<Test> {
    useJUnitPlatform()
    workingDir = rootProject.projectDir
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")
}