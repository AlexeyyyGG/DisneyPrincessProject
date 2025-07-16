val tomcatVersion = "11.0.9"

plugins {
    id("java")
    id ("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java{
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-core:$tomcatVersion")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-el:$tomcatVersion")
    compileOnly ("org.apache.tomcat.embed:tomcat-embed-jasper:$tomcatVersion")
    compileOnly ("org.apache.tomcat.embed:tomcat-embed-websocket:$tomcatVersion")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}

