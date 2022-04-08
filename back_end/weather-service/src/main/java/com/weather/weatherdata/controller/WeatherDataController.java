package com.weather.weatherdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<WeatherDTO> getWeatherReport(@PathVariable("location") String location) {
		return weatherDataService.getWeatherData(location);
	}

}
