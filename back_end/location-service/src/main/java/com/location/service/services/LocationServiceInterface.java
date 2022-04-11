package com.location.service.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.location.service.dto.LocationDTO;
import com.location.service.dto.ResponseLocationDTO;
import com.location.service.entity.Location;
import com.location.service.exceptions.InvalidLocationNameException;
import com.location.service.exceptions.LocationAlreadyExistsException;
import com.location.service.exceptions.LocationNotFoundException;

@Service
public interface LocationServiceInterface {

	ResponseEntity<LocationDTO> setLocation(Location location) throws LocationAlreadyExistsException, InvalidLocationNameException, LocationNotFoundException;
	List<ResponseLocationDTO> getLocationDTO();
	ResponseEntity<HttpStatus> deleteLocation(String locationId);
	Object getWeatherData(String location);
}
