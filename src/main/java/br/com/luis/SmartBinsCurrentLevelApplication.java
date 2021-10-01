package br.com.luis;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class SmartBinsCurrentLevelApplication {
	
	@Autowired
	private SmartBinsLevelService smartBinsLevelService;
	
	private Logger logger = LoggerFactory.getLogger(SmartBinsLevelService.class);

	
	public static void main(String[] args) {
		SpringApplication.run(SmartBinsCurrentLevelApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run() {
		return args -> {
			long minute = 1;
			new Timer().scheduleAtFixedRate(new TimerTask() {
				public void run() {					
					HttpStatus status = smartBinsLevelService.save();
					LocalDateTime dateTime = LocalDateTime.now();
					logger.info(String.format("Task scheduler has run -> %s | Status Http: %s", 
							dateTime, status));
				}
			}, 0, minute * 60000);
		};
	}
}
