package br.com.luis;

import java.util.List;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmartBinsLevelService {
	
	@Autowired
	private SmartBinsLevelFieldRepository smartBinsLevelFieldRepository;
	
	private SmartBinsLevelDto get() {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<SmartBinsLevelDto> response = rest.getForEntity("https://data.randwick.nsw.gov.au/api/records/1.0/search/?dataset=smart-bins-current-level&q=&rows=100&sort=timestamp&timezone=America/Argentina/Buenos_Aires&facet=bin_id&facet=bin_status", 
				SmartBinsLevelDto.class);
		return response.getBody();
	}
	
	public void save() {
		SmartBinsLevelDto smartBinsLevel = get();
		if(nonNull(smartBinsLevel)) {
			List<Record> records = smartBinsLevel.getRecords();
			records.forEach(record -> {
				Field field = record.getFields();
				if(nonNull(field)) {
					SmartBinsLevelField smartBinsLevelField = SmartBinsLevelField.create(field);
					smartBinsLevelFieldRepository.save(smartBinsLevelField);
				}
				
			});
		}
	}
}
