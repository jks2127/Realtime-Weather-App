package com.weather.weatherdata.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.weatherdata.dto.WeatherDTO;

@Service
public class WeatherDataService {
	
	@Value("${service.url}")
	private String endpoint;

	public ResponseEntity<WeatherDTO> getWeatherData(String location) {
		String uri = endpoint + location + "&days=7&aqi=yes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeatherDTO> response = null;

		try {
			Object result = restTemplate.getForObject(uri, Object.class);
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
				.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
			
			response = new ResponseEntity<WeatherDTO>(mapper.map(result, WeatherDTO.class), HttpStatus.OK);
		} catch (Exception e) {
		}
		
		return response;
	}
}
