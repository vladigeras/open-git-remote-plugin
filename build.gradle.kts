import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.vladigeras"
version = System.getenv("APP_VERSION") ?: "0.0.1"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.2.5")
    type.set("IC")
    plugins.set(listOf("Git4Idea"))
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.jvmTarget = JVM_17
    }

    test {
        useJUnitPlatform()
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set(null)
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
