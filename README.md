# springboot3-microservices


1. Create a Project for Service Registry in [[start](https://start.spring.io/).](https://start.spring.io/)

![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/3c416ee2-b55e-4520-aea7-cc9bff97eee7)

Import the Project in IntelliJ or any other IDE.
Change the application.properties to application.yaml
![image](https://github.com/sridhar462/springboot3-microservices/assets/8515080/664b692b-39b8-4407-9dd3-b1ef745e62d2)

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

