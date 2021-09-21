package br.com.luis;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

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
			
			long minute = 60;
			new Timer().scheduleAtFixedRate(new TimerTask() {
				public void run() {
					LocalDateTime dateTime = LocalDateTime.now();
					System.out.print("Task scheduler has run -> "+dateTime);
					
					smartBinsLevelService.save();
				}
			}, 0, minute * 60000);
		};
	}
}
