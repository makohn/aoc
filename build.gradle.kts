plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0" apply false
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}