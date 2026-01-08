plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.15"
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
        kotlin.srcDir("benchmark")
    }
}

benchmark {
    targets {
        register("test")
    }
    configurations {
        named("main") {
            iterations = 2
            warmups = 0
            iterationTime = 1
            iterationTimeUnit = "s"
            mode = "avgt"
            reportFormat = "text"
            outputTimeUnit = "ms"
        }
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
    testImplementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.15")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")
}