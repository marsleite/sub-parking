plugins {
  kotlin("jvm") version "2.0.0"
  kotlin("plugin.spring") version "2.0.0"
  id("java-test-fixtures")
}

apply(plugin = "project-report")

dependencies {
  implementation(project(":domain"))
  implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
  implementation("commons-beanutils:commons-beanutils:1.9.4")
  implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")


  // #### RESILIENCE
  implementation("io.github.resilience4j:resilience4j-spring-boot2:2.1.0")
  implementation("io.github.resilience4j:resilience4j-kotlin:2.1.0")
  runtimeOnly("org.aspectj:aspectjweaver:1.9.19")
  // ##

  implementation("org.apache.commons:commons-text:1.10.0")
  testImplementation("com.h2database:h2")
  testImplementation("io.r2dbc:r2dbc-h2")

  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.0.0")

}

tasks.test {
  environment("APPLICATION", "project-parking")
}

tasks {
  bootJar {
    destinationDirectory.set(file("${rootProject.buildDir}/libs"))
    archiveFileName.set("project-parking.jar")
    enabled = true
  }
  bootRun {
    environment.putIfAbsent("APPLICATION", "project-parking")
  }
}