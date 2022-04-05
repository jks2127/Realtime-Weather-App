package com.weather.weatherdata.dto;

import lombok.Data;

@Data
public class AstroDTO {
	private String sunrise;
	private String sunset;
	private String moonrise;
	private String moonset;
	private String moonPhase;
	private String moonIllumination;
}