package com.location.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.location.service.entity.Location;

@Component
public class Converter {
	public ResponseLocationDTO entityToDTO(Location location) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(location, ResponseLocationDTO.class);
	}
	public List<ResponseLocationDTO> entityToDTOList(List<Location> locationList) {
		return locationList.stream().map(location -> entityToDTO(location)).collect(Collectors.toList());
	}
}
