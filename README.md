🌐 Spring Cloud Microservices Practice Project

This project demonstrates a basic microservices architecture using Spring Cloud with Eureka Server, Spring Cloud Config Server, and a Config Client microservice.
It shows how centralized configuration and service discovery work together in a distributed system.

🏗️ Project Structure
The project consists of 3 components:

Eureka Server – Service Registry
Config Server – Centralized Configuration Management
Config Client (Microservice) – Example service using centralized config and service discovery

Each module plays a specific role in the microservice ecosystem.

⚙️ 1. Eureka Server
📘 Purpose:
The Eureka Server acts as a Service Registry where all microservices register themselves.
It helps services discover and communicate with each other without hardcoding IP addresses or ports.

🧩 Dependencies:
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

🛠️ Configuration (application.properties):
server.port=8761

# Disable self-registration (Eureka Server should not register itself)
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

🧠 Main Class Annotation:
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication { ... }

⚙️ 2. Config Server
📘 Purpose:

The Config Server provides a centralized configuration for all microservices.
It pulls configurations from a Git repository, making it easy to manage properties for all environments (dev, test, prod) in one place.

🧩 Dependencies:
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-config-server</artifactId>
</dependency>

🛠️ Configuration (application.properties):
server.port=8888

# Link to your Git repository containing application configuration files
spring.cloud.config.server.git.uri=https://github.com/<your-username>/<your-config-repo>.git

🧠 Main Class Annotation:
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication { ... }

⚙️ 3. Config Client (Microservice)
📘 Purpose:

This is a sample microservice that:

Registers itself with Eureka Server

Fetches its configuration from the Config Server

Supports runtime refresh of configuration using Spring Actuator

🧩 Dependencies:
<!-- To create REST endpoints -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- To register with Eureka Server -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<!-- To fetch config from Config Server -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

<!-- To enable /refresh endpoint -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

🛠️ Configuration (application.properties):
server.port=8081

spring.application.name=config-client

# Import configuration from config server
spring.config.import=optional:configserver:

# Expose all actuator endpoints (especially /refresh)
management.endpoints.web.exposure.include=*

🧠 Main Class Annotation:
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication { ... }

🔄 Understanding @RefreshScope

The @RefreshScope annotation allows Spring Beans to be refreshed at runtime without restarting the application.

For example:

@RestController
@RefreshScope
public class MessageController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }
}


✅ If you update welcome.message in your Git config file,
you can refresh the bean dynamically by calling:

POST http://localhost:8081/actuator/refresh


This will re-fetch the latest configuration from the Config Server,
and your updated message will appear immediately.

🚀 How to Run the Project

🧩 Step-by-Step Startup Order

Start Eureka Server
mvn spring-boot:run


or run EurekaServerApplication.java
👉 Access at: http://localhost:8761

Start Config Server
mvn spring-boot:run


or run ConfigServerApplication.java
👉 Access at: http://localhost:8888/{application-name}/{profile}

Example:
http://localhost:8888/config-client/default

Start Config Client (Microservice)

mvn spring-boot:run


or run ConfigClientApplication.java
👉 It will automatically fetch configuration from Config Server and register itself with Eureka.

🌈 Summary

Component	Port	Role	Key Annotation
Eureka Server	8761	Service Registry	@EnableEurekaServer
Config Server	8888	Centralized Config Provider	@EnableConfigServer
Config Client	8081	Microservice using centralized config	@EnableDiscoveryClient, @RefreshScope

📚 Key Concepts Recap

Eureka Server → Registers and tracks microservices.
Config Server → Provides configuration files stored in Git.
Config Client → Fetches configs dynamically and registers with Eureka.
@RefreshScope → Allows runtime bean refresh after config changes.
Spring Actuator → Exposes endpoints like /actuator/refresh for runtime operations.

🧠 Useful Endpoints

Service	Endpoint	Description
Eureka Server	http://localhost:8761	View registered services
Config Server	http://localhost:8888/config-client/default	Check client config
Config Client	http://localhost:8081/message	Sample API using config property
Config Client	POST http://localhost:8081/actuator/refresh	Refresh config without restart

🌿 Author
Rumaesa
Java Spring Boot & Angular Fullstack Developer
💻 Practicing Microservices using Spring Cloud Components
