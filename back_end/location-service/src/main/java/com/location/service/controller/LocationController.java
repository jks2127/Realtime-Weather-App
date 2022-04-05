package com.location.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.service.dto.LocationDTO;
import com.location.service.entity.Location;
import com.location.service.services.LocationService;

@RequestMapping("/location")
@RestController
public class LocationController{

	@Autowired
	public LocationService locationService;

	@GetMapping("/all")
	public List<LocationDTO> getLocation() {
		return locationService.getLocationDTO();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{location}")
	public ResponseEntity<Object> getWeatherData(@PathVariable String location,@RequestHeader(name="api_key") String api_key) {
		System.out.println("api_key");
//		if(api_key.equals("jitu@123")) {
			 Object result = locationService.getWeatherData(location,api_key);	
			 return new ResponseEntity<Object>(result, HttpStatus.OK);
//		}
//		return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("")
	public LocationDTO setLocation(@RequestBody Location location) {
		return locationService.setLocation(location);
	}
}
