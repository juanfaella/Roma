package com.RomaAmoblamientos.Roma.RomaApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000" ) // O la URL de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
