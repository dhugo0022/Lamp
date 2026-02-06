plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.14.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly(kotlin("stdlib-jdk8"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}