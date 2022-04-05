package com.location.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.service.WeatherProxy;
import com.location.service.dto.Converter;
import com.location.service.dto.LocationDTO;
import com.location.service.entity.Location;
import com.location.service.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	public LocationRepository locationRepository;
	
	@Autowired
	public Converter converter;
	
	private WeatherProxy proxy;
	public LocationService(WeatherProxy proxy) {
		this.proxy = proxy;
	}
	public LocationDTO setLocation(Location location) {
		Location locationResponse = locationRepository.save(location);
		return converter.entityToDTO(locationResponse);
	}
	
	public List<LocationDTO> getLocationDTO() {
		List<Location> list = locationRepository.findAll();
		return converter.entityToDTOList(list);
	}
	
	public Object getWeatherData(String location, String api_key) {
		System.out.println(location+ api_key);
//		return null;
		return proxy.getWeatherReport(location,api_key);
//		return proxy.getWeatherReport(location);
	}
}
