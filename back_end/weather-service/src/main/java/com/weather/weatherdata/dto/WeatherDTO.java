package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class WeatherDTO {
	private LocationDTO location;
	private CurrentDTO current;
	private ForecastDTO forecast;

}
