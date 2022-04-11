package com.location.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.location.service.dto.WeatherDTO;

@FeignClient(name="weather-service")
public interface WeatherProxy {
	@GetMapping("weatherReport/{location}")
	ResponseEntity<WeatherDTO> getWeatherReport(@PathVariable String location);	
}
