package com.location.service.dto;

import lombok.Data;

@Data
public class LocationDTO {
	private String name;
	private String region;
	private String country;
	private float lat;
	private float lon;
	private String tzId;
	private long localtimeEpoch;
	private String localtime;
}
