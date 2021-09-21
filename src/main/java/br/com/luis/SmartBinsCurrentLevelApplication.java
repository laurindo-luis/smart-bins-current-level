package br.com.luis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartBinsCurrentLevelApplication {
	
	@Autowired
	private SmartBinsLevelService smartBinsLevelService;
	
	public static void main(String[] args) {
		SpringApplication.run(SmartBinsCurrentLevelApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run() {
		return args -> {
			smartBinsLevelService.save();
		};
	}
}
