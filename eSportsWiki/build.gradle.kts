plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {


    //sqlite
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    //mysql
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.json:json:20231013")

    //gson
    implementation("com.google.code.gson:gson:2.9.1")

}

tasks.test {
    useJUnitPlatform()
}