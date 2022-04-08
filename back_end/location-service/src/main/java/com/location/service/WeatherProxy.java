package com.location.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="weather-service")
public interface WeatherProxy {
	@GetMapping("weatherReport/{location}")
	Object getWeatherReport(@PathVariable("location") String location);	
}
