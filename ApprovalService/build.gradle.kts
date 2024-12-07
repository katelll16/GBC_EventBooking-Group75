plugins {
	id("java")
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

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springdoc:springdoc-openapi-webflux-core:1.6.6")
	implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.6")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-oauth2-resource-server")
	implementation("org.springframework.security:spring-security-oauth2-jose")

	// Resilience4j for Spring Boot 3.x
	implementation("io.github.resilience4j:resilience4j-spring-boot3:2.0.2")

	// Spring Cloud Gateway
	implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.0.0")

	// Keycloak Spring Boot Starter
	implementation("org.keycloak:keycloak-spring-boot-starter:20.0.2")

	// PostgreSQL and MongoDB
	implementation("org.postgresql:postgresql")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.testcontainers:testcontainers:1.17.6")
	testImplementation("org.testcontainers:junit-jupiter:1.17.6")
	testImplementation("org.testcontainers:postgresql:1.17.6")
	testImplementation("org.testcontainers:mongodb:1.17.6")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.springfox:springfox-boot-starter:3.0.0") // Add Swagger dependency
	implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}
