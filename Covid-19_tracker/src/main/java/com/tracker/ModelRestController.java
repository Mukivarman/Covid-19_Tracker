package com.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.Services.Locationservice;
import com.tracker.entity.LocationEntity;
import com.tracker.repo.Locations;

@RestController
public class ModelRestController {

	@Autowired
	private Locations location;
	@Autowired
	private Locationservice loc;;
	
	@GetMapping("/alldata")
	public List<LocationEntity> getalldata() {
		return location.findAll();
	}
	
	@GetMapping("/top={id}")
	public List<LocationEntity> fiterby(@PathVariable("id") int id) {
	return	location.filterByTop(id);
	}

	
	
	
}
