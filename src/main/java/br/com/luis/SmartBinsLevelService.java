package br.com.luis;

import java.util.List;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SmartBinsLevelService {
	
	@Autowired
	private SmartBinsLevelFieldRepository smartBinsLevelFieldRepository;
	
	private ResponseEntity<SmartBinsLevelDto> get() {
		RestTemplate rest = new RestTemplate();
		try {
			ResponseEntity<SmartBinsLevelDto> response = rest.getForEntity("https://data.randwick.nsw.gov.au/api/"
					+ "records/1.0/search/?dataset=smart-bins-current-level&q=&rows=100&sort=timestamp&"
					+ "timezone=America/Argentina/Buenos_Aires&facet=bin_id&facet=bin_status", 
					SmartBinsLevelDto.class);
			return response;
		} catch(RestClientException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	public HttpStatus save() {
		ResponseEntity<SmartBinsLevelDto> response = get();
		if(response.getStatusCode().value() == 200) {
			List<Record> records = response.getBody().getRecords();
			records.forEach(record -> {
				Field field = record.getFields();
				if(nonNull(field)) {
					SmartBinsLevelField smartBinsLevelField = SmartBinsLevelField.create(field);
					smartBinsLevelFieldRepository.save(smartBinsLevelField);
				}
			});
		}
		return response.getStatusCode();
	}
}
