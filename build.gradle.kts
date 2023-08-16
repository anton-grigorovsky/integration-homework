import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
}

group = "com.stringconcat"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")
    implementation("org.springframework.retry:spring-retry:2.0.0")

    implementation("jakarta.validation:jakarta.validation-api:3.0.1")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.projectlombok:lombok:1.18.18")

    testImplementation("org.testng:testng:7.5")
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}