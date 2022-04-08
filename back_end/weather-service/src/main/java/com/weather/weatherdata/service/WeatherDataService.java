package com.weather.weatherdata.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.weatherdata.dto.ExceptionFromAPI;
import com.weather.weatherdata.dto.WeatherDTO;

@Service
public class WeatherDataService {

	private final String apiKey = "dd57e8592cde4ad999261418220803";

	public ResponseEntity<WeatherDTO> getWeatherData(String location) {
		String uri = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + " &q=" + location + "&days=7&aqi=yes";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Object result = restTemplate.getForObject(uri, Object.class);
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
				.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
			return new ResponseEntity<WeatherDTO>(mapper.map(result, WeatherDTO.class), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("--------------jitu----"+ e);
			ExceptionFromAPI exceptionObj =new ExceptionFromAPI();
			exceptionObj.setCode(Integer.parseInt(e.getMessage().substring(e.getMessage().indexOf("{\"code\":"), e.getMessage().indexOf(",")).substring(8)));
			exceptionObj.setMessage(e.getMessage().substring(e.getMessage().indexOf("\"message\":\""), e.getMessage().indexOf(".\"}}")).substring(11));
			System.out.println(exceptionObj);
			return new ResponseEntity(exceptionObj ,HttpStatus.BAD_REQUEST);
		}
	}
}
