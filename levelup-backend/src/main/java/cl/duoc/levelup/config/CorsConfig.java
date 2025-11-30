package cl.duoc.levelup.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas las URLs
                        .allowedOrigins(
                                "http://localhost:5173",   // REACT (Vite)
                                "http://localhost:3000"    // (Por si acaso)
                        )
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true) // Importante para cookies/sesiones si las usáramos
                        .maxAge(3600);
            }
        };
    }
}