plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.15"
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }

    register<JavaExec>("run") {
        group = "aoc"
        description = "Run a solution for a specific day"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("Main")
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
            iterations = 10
            warmups = 5
            iterationTime = 1
            iterationTimeUnit = "s"
            mode = "avgt"
            reportFormat = "text"
            outputTimeUnit = "ms"
            include(project.findProperty("include") as String? ?: ".*")
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

    reports {
        html.required.set(false)
        junitXml.required.set(false)
    }

    outputs.upToDateWhen { false }

    afterTest(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
        val res = when(val type = result.resultType) {
            TestResult.ResultType.FAILURE -> "\u001B[31m${type}\u001B[0m"
            TestResult.ResultType.SUCCESS -> "\u001B[32m${type}\u001B[0m"
            TestResult.ResultType.SKIPPED -> "\u001B[33m${type}\u001B[0m"
        }
        val deltaTime = result.endTime - result.startTime
        println(String.format("%-35s %6s %7s %sms", desc.parent?.displayName, desc.displayName, res, deltaTime))
    }))
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.15")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")
}