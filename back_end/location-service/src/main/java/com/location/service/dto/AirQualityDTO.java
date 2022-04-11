package com.location.service.dto;

import lombok.Data;

@Data
public class AirQualityDTO {
	private float no2;
	private float co;
	private float o3;
	private float so2;
	private float pm25;
	private float pm10;
	private int usEpaIndex;
	private int gbDefraIndex;

}
