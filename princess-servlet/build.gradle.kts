val tomcatVersion = "11.0.9"

plugins {
    id("war")
}

dependencies {
    implementation(project(":common"))
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-core:$tomcatVersion")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-el:$tomcatVersion")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-jasper:$tomcatVersion")
    compileOnly("org.apache.tomcat.embed:tomcat-embed-websocket:$tomcatVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}


