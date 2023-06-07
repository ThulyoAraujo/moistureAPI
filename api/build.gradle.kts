import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	//Evita problema com construtores padrão:
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21"
	//Kapt ajuda no processamento das anotações
	kotlin("kapt") version "1.8.0"
}

group = "com.tulio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	//Testes
	testImplementation("org.mockito:mockito-core:3.12.4")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//Repository
	//JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//PostgreSQL
	runtimeOnly("org.postgresql:postgresql")
	////Evita problema com construtores padrão:
	implementation("org.jetbrains.kotlin:kotlin-noarg:1.6.21")

	//MapStruct (Mapper)
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
	}
}
