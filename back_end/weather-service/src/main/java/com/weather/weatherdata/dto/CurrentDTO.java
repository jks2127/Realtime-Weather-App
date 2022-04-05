package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class CurrentDTO {
	private long lastUpdatedEpoch;
	private String lastUpdated;
	private float tempC;
	private float tempF;
	private int isDay;
	private float windMph;
	private float windKph;
	private float windDegree;
	private String windDir;
	private float pressureMb;
	private float pressureIn;
	private float humidity;
	private int cloud;
	private float feelslikeC;
	private float feelslikeF;
	private float  visKm;
	private float visMiles;
	private float uv;
	private float gustMph;
	private float gustKph;
	private ConditionDTO condition;
	private AirQualityDTO airQuality;
	
}
