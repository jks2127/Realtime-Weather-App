package com.location.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.service.dto.ExceptionFromAPI;
import com.location.service.dto.LocationDTO;
import com.location.service.entity.Location;
import com.location.service.services.LocationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/location")
public class LocationController{

	@Autowired
	public LocationService locationService;
	
	/* Fetching the list of locations along with IDs from database */
	@GetMapping("/all")
	public List<LocationDTO> getLocation() {
		return locationService.getLocationDTO();
	}

	/* Fetching weather details based on the location name passed in url path */
	@GetMapping("/{location}")
	public ResponseEntity<Object> getWeatherData(@PathVariable String location) {
		Object result = locationService.getWeatherData(location);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	/* Inserting location name into database */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<LocationDTO> setLocation(@RequestBody Location location) {
		System.out.println("------------jugal--------------");
		Object result = locationService.getWeatherData(location.getLocationName());

		/* checking for a specific string which comes in "result" when there is some error.
		 * If there is error then taking out only the error message and error code in "exceptionObj" object and sending it as response*/
		if(!(result.toString().contains("ExceptionFromAPI(code=1006"))) {
			return locationService.setLocation(location);
		}else {
			ExceptionFromAPI exceptionObj =new ExceptionFromAPI();
			exceptionObj.setCode(Integer.parseInt(result.toString().substring(result.toString().indexOf("(code="),result.toString().indexOf(", message")).substring(6)));
			exceptionObj.setMessage(result.toString().substring(result.toString().indexOf(", message="), result.toString().indexOf("),")).substring(10));
			return new ResponseEntity(exceptionObj ,HttpStatus.BAD_REQUEST);
		}
	}
	
	/* Deleting location name from database */
	@DeleteMapping("{locationId}")
	public ResponseEntity<HttpStatus> deleteLocation(@PathVariable String locationId) {
		System.out.println(locationId.getClass().getTypeName());
		return this.locationService.deleteLocation(locationId);
	}
}
