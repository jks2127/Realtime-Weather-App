package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class DayDTO {
	private float maxtempC;
	private float maxtempF;
	private float mintempC;
	private float mintempF;
	private float avgtempC;
	private float avgtempF;
	private float maxwindMph;
	private float maxwindKph;
	private float totalprecipMm;
	private float totalprecipIn;
	private float avgvisKm;
	private float avgvisMiles;
	private float avghumidity;
	private int dailyWillItRain;
	private int dailyChanceOfRain;
	private int dailyWillItSnow;
	private int dailyChanceOfSnow;
	private ConditionDTO condition;
	private int uv;
}