package com.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tracker.entity.LocationEntity;

import java.util.List;
import java.util.Optional;




public interface Locations extends JpaRepository<LocationEntity,Integer>{

	boolean existsByCountryAndState(String country, String state);
	
	Optional<LocationEntity> findByCountryAndState(String country, String state);
	
	
	@Query(value = "SELECT * FROM location_entity ORDER BY latest_total DESC LIMIT ?1", nativeQuery = true)
	List<LocationEntity> filterByTop(int limit);

}
