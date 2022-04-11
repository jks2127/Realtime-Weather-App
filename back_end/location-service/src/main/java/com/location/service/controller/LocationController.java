package com.location.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.service.dto.LocationDTO;
import com.location.service.dto.ResponseLocationDTO;
import com.location.service.dto.WeatherDTO;
import com.location.service.entity.Location;
import com.location.service.exceptions.InvalidLocationNameException;
import com.location.service.exceptions.LocationAlreadyExistsException;
import com.location.service.exceptions.LocationNotFoundException;
import com.location.service.services.LocationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/location")
@Transactional
public class LocationController{

	@Autowired
	public LocationService locationService;
	
	/**
	 *  Fetching the list of locations along with IDs from database 
	 *  and min max temperature from weather data of each location
	 *  
	 *  @return {@link List<LocationDTO>}
	 */
	@GetMapping("/all")
	public List<ResponseLocationDTO> getLocation() {
		return locationService.getLocationDTO();
	}

	/**
	 * Fetching weather details based on the location name passed in url path 
	 * 
	 * @param location
	 * 
	 * @return {@link ResponseEntity<Object>}
	 */
	@GetMapping("/{location}")
	public ResponseEntity<WeatherDTO> getWeatherData(@PathVariable String location) {
		
		return locationService.getWeatherData(location);
	}
	
	/**
	 * Inserting location name into database 
	 *
	 * @param location
	 * 
	 * @return {@link ResponseEntity<LocationDTO>}
	 * 
	 * @throws {@link LocationAlreadyExistsException, InvalidLocationNameException, LocationNotFoundException}
	 */
	@PostMapping
	public ResponseEntity<LocationDTO> setLocation(@RequestBody Location location) throws LocationAlreadyExistsException, InvalidLocationNameException, LocationNotFoundException  {

		return locationService.setLocation(location);
	}
	
	/**
	 * Deleting location name from database 
	 *
	 * @param locationId
	 * 
	 * @return {@link ResponseEntity<HttpStatus>}
	 */
	@DeleteMapping("{locationId}")
	public ResponseEntity<HttpStatus> deleteLocation(@PathVariable String locationId) {

		return this.locationService.deleteLocation(locationId);
	}
}
