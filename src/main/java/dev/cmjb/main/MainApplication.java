package dev.cmjb.main;

import dev.cmjb.main.properties.StorageProperties;
import dev.cmjb.main.services.UploadedFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.cmjb.main"})
@EnableConfigurationProperties(StorageProperties.class)
public class MainApplication {

	@Value("${cors.allowedOrigin}")
	private String allowedOrigin;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UploadedFileService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedOrigins(allowedOrigin);
			}
		};
	}
}
