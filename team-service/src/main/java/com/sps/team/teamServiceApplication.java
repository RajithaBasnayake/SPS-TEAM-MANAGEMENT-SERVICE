package com.sps.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Team Management Microservice Main Application
 *
 * This is a standalone microservice for managing cricket teams
 * Part of SPS Cricket Club Management System
 *
 * @author SPS Cricket Club
 * @version 1.0
 */
@SpringBootApplication
public class TeamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamServiceApplication.class, args);
		System.out.println("===============================================");
		System.out.println("Team Management Microservice Started!");
		System.out.println("Server running on: http://localhost:8081");
		System.out.println("Swagger UI: http://localhost:8081/team-service/swagger-ui.html");
		System.out.println("API Base URL: http://localhost:8081/team-service/api/teams");
		System.out.println("===============================================");
	}

	/**
	 * CORS Configuration for frontend integration
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000", "http://localhost:3001")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true)
						.maxAge(3600);
			}
		};
	}
}
