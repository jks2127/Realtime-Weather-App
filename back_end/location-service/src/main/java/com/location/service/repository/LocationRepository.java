package com.location.service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.location.service.entity.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{
	List<Location> findByLocationName(String locationName);
}
