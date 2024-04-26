# springboot3-microservices


1. Create a Project for Service Registry in [(https://start.spring.io/).](https://start.spring.io/)

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/3c416ee2-b55e-4520-aea7-cc9bff97eee7)

Import the Project in IntelliJ or any other IDE.
**Change the application.properties to application.yaml**

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/9f0caeb2-a2df-496c-ba25-687cd122d884)


```
package com.payil.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}

```


pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.payil</groupId>
	<artifactId>service-registry</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>service-registry</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2023.0.1</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```
```
spring:
  application:
    name: service-registry
server:
  port: 8761
eureka:
  instance:
    hostname: localhost
  client:
    fetch-registry: false
    register-with-eureka: false
    service-url:
      defaultZone : http://${eureka.instance.hostname}:${server.port}/eureka/

```

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/fa6963dc-2e34-44dd-92f1-f74c54c3df90)


**Create a Department Service**

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/3430faac-170d-4386-b676-f9f020214e0e)


application.yaml


![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/ae1b428f-7874-44d3-ab8f-103b491b1ff7)


```
spring:
  application:
    name: course-service

eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka
server:
      port: 8091
```

**CourseServiceApplication.java**
```
package com.payil.courseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}
```


![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/5bcdeccb-d612-44e6-8c2a-d974727a1a42)

**Student Service**

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/1a332a6a-0f9f-49ed-ade3-2ded4af69eb5)


![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/5d816c90-c256-446d-8bf8-4c8ef3183e98)


**application.yaml**

```
spring:
  application:
    name: student-service

eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka
server:
      port: 8092
```

**StudentServiceApplication**
```
package com.payil.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}

```

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/edef2918-e2fd-44ba-a36d-976494dbecec)

**Config Server**

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/7306582a-0a9f-48fb-aef1-9e2257cbf600)

**application.yaml**

```
spring:
  application:
    name: config-server
  profiles:
    active: native
server:
  port: 9090

```

Create a folder in resorces "config" and add the application.yaml for each services. The naming should be <service-name>.yaml

By default it will identify with the application name.

**course-service.yaml**

```
server:
  port: 8091
eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka

```

**student-service.yaml**

```
server:
  port: 8092
eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka

```
Add Mentioned Dependency in Course and Student Service

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/407e59cb-99ce-41d7-8dab-a82e5c479ffd)

```
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
```


Updated **application.yaml** file in Student-service

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.payil</groupId>
	<artifactId>student-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>student-service</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2023.0.1</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

Updated **application.yaml** file in Course-service

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.payil</groupId>
	<artifactId>course-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>course-service</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
		<spring-cloud.version>2023.0.1</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```


![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/d819000a-066f-4585-982f-77266bfb8a7a)

As the config are moved to config server, by default by adding above dependency and by providing url it will try to look from config server.

Updated course-service **application.yaml**

```
spring:
  application:
    name: course-service
  config:
    import: "optional:configserver:http://localhost:9090"


```

Updated student-service **application.yaml**
```
spring:
  application:
    name: student-service
  config:
    import: "optional:configserver:http://localhost:9090"

```


ZIPKIN Server

Download Zipkin from its official page

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/951d863a-7c37-45c7-9dd8-28279a4ce3b3)

```
java -jar zipkin-server-3.3.0-exec


```

By default it will run in 9411 port.
![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/0fc08b8e-29ef-437b-8641-5bb86ed6d3c7)


![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/81e18082-0c1d-46df-90ff-3cf42fb33623)


Add ZipKin Dependency in course-service and student-service

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/2cecdc76-bed3-46c4-a59e-8001172ae13b)

This zipkin will also include acctuator. As Acctuator is already available copy below two dependency to course-service and student-service

```
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-tracing-bridge-brave</artifactId>
    </dependency>
    <dependency>
      <groupId>io.zipkin.reporter2</groupId>
      <artifactId>zipkin-reporter-brave</artifactId>
    </dependency>

```

To see the logs we have to add tracing probability in application.yaml file. Lets add it both course-service and student-service

**course-service.yaml**

```
server:
  port: 8091
eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka
management:
  tracing:
    sampling:
      probability: 1.0
```
**student-service.yaml**
```
server:
  port: 8092
eureka:
  client:
    service-url:
      defaultZone : http://localhost:8761/eureka
management:
  tracing:
    sampling:
      probability: 1.0
```
![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/2fb44998-0c48-4b2b-9033-6189afd42129)

HTTP Exchange

Course Service will call Student Service and get details from that service.

For HTTPExchange we need webflex depdency. Add this in Course service

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/8c6d66cb-bef1-4619-88fc-753ce0f87a54)


```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
```


```
package com.payil.courseservice.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface StudentClient {

     @GetExchange("/student/course/{courseId}")
     String sendMsg(@PathVariable("courseId") String courseId);
}

```

```
package com.payil.courseservice.config;

import com.payil.courseservice.client.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient getWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://student-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }


    @Bean
    public StudentClient buildStudentClient(){
        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(getWebClient()))
                .build()
                .createClient(StudentClient.class);
    }
}

```


```
package com.payil.courseservice.controller;

import com.payil.courseservice.client.StudentClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private StudentClient studentClient;

    @GetMapping
    public String helloCourseController(){
        LOGGER.info("Welcome to Controller - Course");
        return "Welcome to Course Controller";
    }

    @GetMapping("/with-student")
    public String getStudentInfo(){
        return "Welcome to Course Controller " + studentClient.sendMsg("1");
    }
}

```


```
package com.payil.studentservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public String helloStudentController(){
        LOGGER.info("Welcome to Controller - Student");
        return "Welcome to Student Controller";
    }

    @GetMapping("/course/{courseId}")
    public String sendMsg(@PathVariable("courseId") String courseId){
        return "Welcome to Student Controller - for Course Id" + courseId;
    }

}

```
