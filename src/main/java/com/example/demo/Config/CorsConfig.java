package com.example.demo.Config;

// src/main/java/.../config/CorsConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration cors = new CorsConfiguration();
        // IMPORTANT: must be the exact origin including https
        cors.setAllowedOrigins(List.of(
            "https://doctor-appoinment-system-frontend.onrender.com" // your React app
        ));
        cors.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        cors.setAllowedHeaders(List.of("Authorization","Content-Type","Accept","Origin","X-Requested-With"));
        cors.setExposedHeaders(List.of("Authorization","Location"));
        cors.setAllowCredentials(true); // only if you use cookies/auth headers
        // cache preflight for 1 hour
        cors.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter(source);
    }
}
