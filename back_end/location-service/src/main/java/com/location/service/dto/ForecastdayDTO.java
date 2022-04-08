package com.location.service.dto;

import lombok.Data;

@Data
public class ForecastdayDTO {
	private String date;
	private long dateEpoch;
	private DayDTO day;
}