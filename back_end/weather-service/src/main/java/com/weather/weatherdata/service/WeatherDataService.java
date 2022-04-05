package com.weather.weatherdata.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.weatherdata.dto.WeatherDTO;

@Service
public class WeatherDataService {

	private final String apiKey = "dd57e8592cde4ad999261418220803";

	public WeatherDTO getWeatherData(String location) {
		String uri = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + " &q=" + location + "&days=7&aqi=yes";

		RestTemplate restTemplate = new RestTemplate();
		Object result = restTemplate.getForObject(uri, Object.class);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
				.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);

		return modelMapper.map(result, WeatherDTO.class);
	}
}
