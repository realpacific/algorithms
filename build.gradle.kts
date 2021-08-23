plugins {
    `java-lang`
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.dokka") version "1.5.0"
}

repositories {
    jcenter()
    mavenCentral()
}

sourceSets {
    this.main {
        java {
            srcDir("src")
        }
    }
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-test:1.4.21")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.5.0")
}

val failures = mutableListOf<String>()

tasks.create("execClass", type = JavaExec::class) {
    val javaExec = this
    javaExec.isIgnoreExitValue = true
    sourceSets.main {
        javaExec.classpath = runtimeClasspath
    }
    jvmArgs = listOf("-ea")
    main = project.properties["mainClass"].toString()

    doLast {
        if (executionResult.get().exitValue != 0) {
            println(">>> Execution failed")

            val logs = File("jvm-logs.txt")
            if (!logs.exists()) {
                logs.createNewFile()
            }
            logs.appendText("Execution failed ${project.properties["mainClass"].toString()}\n")
            executionResult.get().rethrowFailure()
        }
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(projectDir.resolve("docs"))
}