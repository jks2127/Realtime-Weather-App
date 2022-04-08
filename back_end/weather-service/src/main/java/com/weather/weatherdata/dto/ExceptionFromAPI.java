package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class ExceptionFromAPI {
	private int code;
	private String message;

}
