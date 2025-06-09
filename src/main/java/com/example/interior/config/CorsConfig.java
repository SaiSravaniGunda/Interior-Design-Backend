package com.example.interior.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebMvc
public class CorsConfig {
	
	
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")  
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true)  
                        .allowedHeaders("*");
            }
        };
    }
}
