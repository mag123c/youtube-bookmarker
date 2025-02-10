plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.mag1c.youtube-bookmark"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") // REST API
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-redis") // Redis
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // JSON 직렬화
    implementation("mysql:mysql-connector-java:8.0.33") // MySQL 드라이버
    implementation("org.jetbrains.kotlin:kotlin-reflect") // 리플렉션 기능
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // 코루틴

    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")  // kotest core
    testImplementation("io.kotest:kotest-assertions-core:5.7.2") // kotest assertions
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3") // spring integration
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.13.5")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
