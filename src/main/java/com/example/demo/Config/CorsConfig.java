package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {
	 @Bean
	  public WebMvcConfigurer corsConfigurer() {
	    String allowed = System.getenv().getOrDefault(
	        "CORS_ALLOWED_ORIGIN", "https://your-frontend.onrender.com");
	    return new WebMvcConfigurer() {
	      @Override public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	          .allowedOrigins(allowed)
	          .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
	          .allowCredentials(true);
	      }
	    };
	  }

}
