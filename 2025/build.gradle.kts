plugins {
    kotlin("jvm")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks.withType<JavaExec> {
    isIgnoreExitValue = true
}

dependencies {
    implementation(project(":Library"))
}