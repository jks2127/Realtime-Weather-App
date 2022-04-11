package com.location.service.dto;

import lombok.Data;

@Data
public class HourDTO {
	private long timeEpoch;
	private String time;
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
	private float windchillC;
	private float windchillF;
	private float precipMm;
	private float precipIn;
	private float heatindexC;
	private float heatindexF;
	private float dewpointC;
	private float dewpointF;
	private int willItRain;
	private int chanceOfRain;
	private int willItSnow;
	private int chanceOfSnow;

}