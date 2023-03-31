
package com.wittybrains.busbookingsystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.StationDTO;

import com.wittybrains.busbookingsystem.service.StationService;


	
	@RestController
	@RequestMapping("/stations")
	public class StationController {
		@Autowired
	    private StationService stationService;
	    
	    @PostMapping
	    public ResponseEntity<List<StationDTO>> createStations(@RequestBody List<StationDTO> stationList) {
	        List<StationDTO> createdStations = stationService.createStations(stationList);
	        return ResponseEntity.ok().body(createdStations);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<StationDTO> getStation(@PathVariable Long id) {
	        StationDTO stationDTO = stationService.getStationById(id);
	        return ResponseEntity.ok().body(stationDTO);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<StationDTO> updateStation(@PathVariable Long id, @RequestBody StationDTO stationDTO) {
	        StationDTO updatedStationDTO = stationService.updateStation(id, stationDTO);
	        return ResponseEntity.ok().body(updatedStationDTO);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
	        stationService.deleteStation(id);
	        return ResponseEntity.ok().build();
	    }
	}


