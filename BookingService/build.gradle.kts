plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.group75"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.github.resilience4j:resilience4j-spring-boot3:2.0.2")

	// Update Testcontainers to the latest version
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.testcontainers:testcontainers:1.20.4")
	testImplementation("org.testcontainers:junit-jupiter:1.20.4")
	testImplementation("org.testcontainers:postgresql:1.20.4")
	testImplementation("org.testcontainers:mongodb:1.20.4")
	testImplementation("org.testcontainers:kafka:1.20.4")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.springfox:springfox-boot-starter:3.0.0") // Add Swagger dependency
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Kafka dependencies
	implementation("org.springframework.kafka:spring-kafka:3.3.0")
	testImplementation("org.springframework.kafka:spring-kafka-test:3.3.0")

	compileOnly("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}
