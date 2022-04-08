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
	public ResponseEntity<LocationDTO> setLocation(Location location) {
		List<Location> found = locationRepository.findByLocationName(location.getLocationName());
		System.out.println(found.size());
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
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		ForecastDTO resultDTO = mapper.map(result, ForecastDTO.class);
//		System.out.println(resultDTO);
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
			System.out.println("inside try");
			this.locationRepository.deleteById(Integer.parseInt(locationId));
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("inside catch");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
