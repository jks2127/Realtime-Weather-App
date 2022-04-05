package com.weather.weatherdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherdata.dto.WeatherDTO;
import com.weather.weatherdata.service.WeatherDataService;

@RestController
@RequestMapping("/weatherReport")
public class WeatherDataController {

	@Autowired
	private WeatherDataService weatherDataService;

	@GetMapping("/{location}")
	public ResponseEntity<Object> getWeatherReport(@PathVariable("location") String location, @RequestHeader("api_key") String apiKey) {
		if (apiKey.equals("jitu@123")) {
			 WeatherDTO result = weatherDataService.getWeatherData(location);
			 return new ResponseEntity<Object>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
	}
	
//	@GetMapping("/{location}")
//	public WeatherDTO getWeatherReport(@PathVariable("location") String location) {
//		return weatherDataService.getWeatherData(location);
//	}

}
