plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21" apply false
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}