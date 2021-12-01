plugins {
    java
    kotlin("jvm") version "1.5.31"
}

repositories {
    jcenter()
}

sourceSets {
    this.main {
        java {
            srcDir("src")
        }
    }
    this.test {
        java {
            srcDir("test")
        }
    }
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")!!
        .because("For writing tests for assertion methods")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.31")!!
        .because("For writing tests for assertion methods")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

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
