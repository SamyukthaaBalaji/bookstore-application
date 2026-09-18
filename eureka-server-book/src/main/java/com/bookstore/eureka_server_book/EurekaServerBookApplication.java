package com.bookstore.eureka_server_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerBookApplication.class, args);
	}

}
