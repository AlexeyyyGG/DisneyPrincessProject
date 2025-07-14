plugins {
    id("java")
    id ("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-core:11.0.9")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-el:11.0.9")
    compileOnly ("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.9")
    compileOnly ("org.apache.tomcat.embed:tomcat-embed-websocket:11.0.9")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.33")
}


