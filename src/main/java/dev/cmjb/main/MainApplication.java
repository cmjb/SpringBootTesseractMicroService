package dev.cmjb.main;

import dev.cmjb.main.properties.StorageProperties;
import dev.cmjb.main.services.UploadedFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.cmjb.main"})
@EnableConfigurationProperties(StorageProperties.class)
public class MainApplication {

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

}
