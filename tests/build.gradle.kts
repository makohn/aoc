plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

dependencies {
    implementation(project(":util"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")
    api(kotlin("test-junit5"))
}