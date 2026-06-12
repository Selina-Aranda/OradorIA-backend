package com.utp.DemoOratorIA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoOratorIaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOratorIaApplication.class, args);

		System.out.print("El proyecto corre en el http://localhost:8001/main");
	}

}
