package com.location.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.location.service.client.WeatherProxy;
import com.location.service.dto.Converter;
import com.location.service.dto.ForecastDTO;
import com.location.service.dto.LocationDTO;
import com.location.service.dto.ResponseLocationDTO;
import com.location.service.dto.WeatherDTO;
import com.location.service.entity.Location;
import com.location.service.exceptions.InvalidLocationNameException;
import com.location.service.exceptions.LocationAlreadyExistsException;
import com.location.service.exceptions.LocationNotFoundException;
import com.location.service.repository.LocationRepository;
import com.location.service.utils.LocationUtility;

@Service
public class LocationService implements LocationServiceInterface {
	@Autowired
	public LocationRepository locationRepository;

	@Autowired
	public Converter converter;

	@Autowired
	private WeatherProxy proxy;

	/**
	 * Inserting location name into database
	 *  
	 * @param location
	 * 
	 * @return {@link ResponseEntity<LocationDTO>}
	 * 
	 * @throws {@link LocationAlreadyExistsException, InvalidLocationNameException, LocationNotFoundException}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<LocationDTO> setLocation(Location location) throws LocationAlreadyExistsException, InvalidLocationNameException, LocationNotFoundException {
		List<Location> found = locationRepository.findByLocationName(location.getLocationName());
		
		if(location.getLocationName().isEmpty()) {
			throw new InvalidLocationNameException(LocationUtility.INVALID_LOCATION_NAME);
		}else if (found.size() != 0) {
			throw new LocationAlreadyExistsException(LocationUtility.LOCATION_ALREADY_EXISTS);
		}else if (getWeatherData(location.getLocationName()).getBody() == null) {
			throw new LocationNotFoundException(LocationUtility.MATCHING_LOCATION_NOT_FOUND);
		}else {
			Location locationResponse = locationRepository.save(location);
			
			return new ResponseEntity(converter.entityToDTO(locationResponse), HttpStatus.OK);			
		}
	}
	
	/** 
	 * Fetching the list of locations along with IDs from database then mapping them 
	 * to ResponseLocationDTO where we have fields for min max temperature that will 
	 * be added using map funtion.
	 * 
	 * @return {@link List<ResponseLocationDTO>}
	 */
	public List<ResponseLocationDTO> getLocationDTO() {
		List<Location> list = locationRepository.findAll();
		List<ResponseLocationDTO> listDto = converter.entityToDTOList(list);
		
		return listDto.stream().map(item -> map(item)).collect(Collectors.toList());
	}
	
	public ResponseLocationDTO map(ResponseLocationDTO item) {
		ResponseEntity<WeatherDTO> result = getWeatherData(item.getLocationName());
		// Mapping min max temp coming from api and keeping them in "resultDTO" object along with the respective location name then sending it back
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		ForecastDTO resultDTO = mapper.map(result.getBody(), ForecastDTO.class);
		item.setMaxtempC(resultDTO.getForecastday().get(0).getDay().getMaxtempC());
		item.setMintempC(resultDTO.getForecastday().get(0).getDay().getMintempC());

		return item;
	}
	
	/**
	 * Deleting location name from DB
	 * 
	 * @param locationId
	 * 
	 * @return {@link ResponseEntity<HttpStatus>}
	 */
	public ResponseEntity<HttpStatus> deleteLocation(String locationId) {
		try {
			this.locationRepository.deleteById(Integer.parseInt(locationId));
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *  Fetching weather details based on the location name passed in url path
	 *  
	 *   @param location
	 *   
	 *   @return {@link ResponseEntity<WeatherDTO>}
	 */
	public ResponseEntity<WeatherDTO> getWeatherData(String location) {
		
		return proxy.getWeatherReport(location);
	}

}
