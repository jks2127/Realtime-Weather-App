package com.location.service.dto;

import lombok.Data;

@Data
public class ResponseLocationDTO {

		private String locationId;
		private String locationName;
		private float maxtempC;
		private float mintempC;
}
