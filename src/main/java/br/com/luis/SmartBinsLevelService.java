package br.com.luis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmartBinsLevelService {
	
	public void get() {
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<SmartBinsLevel> response = rest.getForEntity("https://data.randwick.nsw.gov.au/api/records/1.0/search/?dataset=smart-bins-current-level&q=&rows=100&sort=timestamp&timezone=America/Argentina/Buenos_Aires&facet=bin_id&facet=bin_status", 
				SmartBinsLevel.class);
		
		SmartBinsLevel smartBinsLevel = response.getBody();
		System.out.print(smartBinsLevel);
	}
}
