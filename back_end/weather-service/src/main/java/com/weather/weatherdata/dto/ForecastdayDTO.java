package com.weather.weatherdata.dto;

import java.util.List;

import lombok.Data;

@Data
public class ForecastdayDTO {
	private String date;
	private long dateEpoch;
	private DayDTO day;
	private AstroDTO astro;
	private List<HourDTO> hour;
}