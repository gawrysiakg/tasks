package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@OpenAPIDefinition
@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {SpringApplication.run(TasksApplication.class, args);
	}


}
