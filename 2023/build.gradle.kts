plugins {
    kotlin("jvm")
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
}

tasks.withType<Test> {
    useJUnitPlatform()
    workingDir = rootProject.projectDir
}

dependencies {
    implementation(project(":utils"))
    testImplementation(project(":test-utils"))
}