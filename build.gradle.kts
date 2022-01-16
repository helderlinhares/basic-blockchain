import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "me.hl"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2020.0.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
	testImplementation("junit:junit")
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.google.code.gson:gson:2.8.9")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

configurations.all {
	resolutionStrategy.eachDependency {
		if (requested.group == "ch.qos.logback") {
			useVersion("[1.2.10, 1.3)")
			because("Fixes critical logback vulnerability in 1.2.8+, last version now 1.2.10. " +
					"Limited to 1.3 because of a legacy alpha 1.3 version.")
		}
	}
	exclude(group = "org.apache.logging.log4j")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnit()
}
