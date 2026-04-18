plugins {
    kotlin("jvm") version "2.3.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.15"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
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

    afterTest(
        KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            val res = when (result.resultType) {
                TestResult.ResultType.FAILURE -> "\u001B[31mFAILED\u001B[0m"
                TestResult.ResultType.SUCCESS -> "\u001B[32mPASSED\u001B[0m"
                TestResult.ResultType.SKIPPED -> "\u001B[33mSKIPPED\u001B[0m"
            }
            println("${desc.parent?.className} > ${desc.displayName} $res")

            if (result.exception != null) {
                result.exception!!.printStackTrace()
            }
        }),
    )
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.15")
}
