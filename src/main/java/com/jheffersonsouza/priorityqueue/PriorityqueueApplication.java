package com.jheffersonsouza.priorityqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriorityqueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriorityqueueApplication.class, args);
	}

}
