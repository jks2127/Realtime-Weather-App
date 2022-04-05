package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class ConditionDTO {
	private String text;
	private String icon;
	private int code;

}
