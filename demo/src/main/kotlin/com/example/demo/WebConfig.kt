package com.example.demo

import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http:://localhost:3000", "http:://localhost:8080", "http:://localhost:4200", "http://192.168.1.81:3000")
            .allowCredentials(true)
            .allowedMethods("GET", "PUT", "POST", "DELETE", "HEAD", "OPTIONS")
    }
}