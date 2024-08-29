plugins {
  kotlin("jvm") version "2.0.0"
  id("java-test-fixtures")
}

apply(plugin = "project-report")

tasks {
  jar { enabled = true }
  bootJar { enabled = false }
}