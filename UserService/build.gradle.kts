plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.group75"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(23))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Testcontainers dependencies
	testImplementation("org.testcontainers:testcontainers:1.18.0")
	testImplementation("org.testcontainers:junit-jupiter:1.18.0")
	testImplementation("org.testcontainers:postgresql:1.18.0")
	testImplementation("org.testcontainers:mongodb:1.18.0")

	// JUnit dependencies
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}
