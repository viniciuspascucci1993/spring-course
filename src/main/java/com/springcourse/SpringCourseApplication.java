package com.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringCourseApplication extends SpringBootServletInitializer {

	/*
	* Metodo necessário para implantação em servidor externo Ex: AWS
	* */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringCourseApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}

}
