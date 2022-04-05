package com.weather.weatherdata.dto;

import java.util.List;

import lombok.Data;

@Data
public class ForecastDTO {
	private List<ForecastdayDTO> forecastday;

}
