val springVersion = "6.2.9"
plugins {
    id("war")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework:spring-context:$springVersion")
    implementation("org.springframework:spring-web:$springVersion")
    implementation("org.springframework:spring-webmvc:$springVersion")
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}
