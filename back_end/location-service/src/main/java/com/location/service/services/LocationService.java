package com.location.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.location.service.WeatherProxy;
import com.location.service.dto.Converter;
import com.location.service.dto.ExceptionFromAPI;
import com.location.service.dto.ForecastDTO;
import com.location.service.dto.LocationDTO;
import com.location.service.entity.Location;
import com.location.service.repository.LocationRepository;

@Service
public class LocationService {
	@Autowired
	public LocationRepository locationRepository;

	@Autowired
	public Converter converter;

	@Autowired
	private WeatherProxy proxy;

	/* Inserting location name into database */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<LocationDTO> setLocation(Location location) {
		List<Location> found = locationRepository.findByLocationName(location.getLocationName());
		/* Checking whether the location is already in the DB or not
		 * If already exist , adding error message and code in "exception" object and sending it back*/
		if (found.size() == 0) {
			Location locationResponse = locationRepository.save(location);
			return new ResponseEntity(converter.entityToDTO(locationResponse), HttpStatus.OK);			
		}
		ExceptionFromAPI exception = new ExceptionFromAPI();
		exception.setMessage("Location already exists");
		exception.setCode(400);
		return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}
	
	/* Fetching the list of locations along with IDs from database */
	public List<LocationDTO> getLocationDTO() {
		List<Location> list = locationRepository.findAll();
		List<LocationDTO> listDto = converter.entityToDTOList(list);
		return listDto.stream().map(item -> map(item)).collect(Collectors.toList());
	}
	public LocationDTO map(LocationDTO item) {
		Object result = getWeatherData(item.getLocationName());
		/* Mapping min max temp coming from api and keeping them in "resultDTO" object along with the respective location name then sending it back*/
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		ForecastDTO resultDTO = mapper.map(result, ForecastDTO.class);
		item.setMaxtempC(resultDTO.getForecastday().get(0).getDay().getMaxtempC());
		item.setMintempC(resultDTO.getForecastday().get(0).getDay().getMintempC());

		return item;
	}

	/* Fetching weather details based on the location name passed in url path */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getWeatherData(String location) {
		try {
			return proxy.getWeatherReport(location);
			
		} catch (Exception e) {
			/* If any exception occurs,
			 * then taking error message and code then setting in "exceptionObj" object and sending it back*/
			ExceptionFromAPI exceptionObj = new ExceptionFromAPI();
			System.out.println(e);
			exceptionObj.setCode(Integer.parseInt(e.getMessage()
					.substring(e.getMessage().indexOf("{\"code\":"), e.getMessage().indexOf(",")).substring(8)));
			exceptionObj.setMessage(e.getMessage()
					.substring(e.getMessage().indexOf("\"message\":\""), e.getMessage().indexOf("\"}]")).substring(11));
			return new ResponseEntity(exceptionObj, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<HttpStatus> deleteLocation(String locationId) {
		try {
			this.locationRepository.deleteById(Integer.parseInt(locationId));
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
