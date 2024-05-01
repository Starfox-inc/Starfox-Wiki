plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.Starfox"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}
 
repositories {
	mavenCentral()
}

dependencies {
	//springboot
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.data:spring-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	//displaying html website
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	//annotations for models and controllers
	implementation("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	implementation("org.modelmapper:modelmapper:2.3.5")

    //sqlite
	runtimeOnly("mysql:mysql-connector-java:8.0.33")


    //mysql

	
	implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.json:json:20231013")

    //gson
    implementation("com.google.code.gson:gson:2.9.1")

	//jbcrypt for password hashing
    implementation ("org.mindrot:jbcrypt:0.4")

	//validation	
	implementation ("org.springframework.boot:spring-boot-starter-validation")

	//thymeleaf
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
