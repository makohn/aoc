plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.benchmark")
}

sourceSets {
    create("benchmark") {
        kotlin.srcDir("bench")
    }
}


kotlin {
    target {
        compilations.getByName("benchmark")
            .associateWith(compilations.getByName("main"))
    }
}

benchmark {
    targets {
        register("benchmark")
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

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.15")
}