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

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
}