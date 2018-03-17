package com.example.phonebook.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class TracingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingServiceApplication.class, args);
	}
}
