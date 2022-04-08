package com.location.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class ForecastDTO {
	private List<ForecastdayDTO> forecastday;

}
