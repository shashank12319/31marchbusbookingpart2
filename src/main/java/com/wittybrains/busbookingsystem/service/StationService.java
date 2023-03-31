package com.wittybrains.busbookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.StationDTO;
import com.wittybrains.busbookingsystem.exception.ResourceNotFoundException;
import com.wittybrains.busbookingsystem.exception.StationNotFoundException;
import com.wittybrains.busbookingsystem.model.Station;
import com.wittybrains.busbookingsystem.repository.StationRepository;



@Service
public class StationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StationService.class);

	private final StationRepository stationRepository;

	public StationService(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	public String createStation(StationDTO stationDTO) {
		if (StringUtils.isBlank(stationDTO.getStationCode())) {
			throw new IllegalArgumentException("Station code must not be null or empty");
		}
		Optional<Station> optionalStation = stationRepository.findByStationCode(stationDTO.getStationCode());
		if (optionalStation.isPresent()) {
			throw new IllegalArgumentException("Station code must be unique");
		}
		Station station = new Station(stationDTO.getName(), stationDTO.getStationCode());
		stationRepository.save(station);
		LOGGER.info("Created station with ID: {}", station.getId());
		return "Successfully created station with ID: " + station.getId();
	}

	public Station getStationByCode(String code) {
		if (StringUtils.isBlank(code)) {
			throw new IllegalArgumentException("Station code must not be null or empty");
		}
		Optional<Station> optionalStation = stationRepository.findByStationCode(code);
		if (optionalStation.isPresent()) {
			Station station = optionalStation.get();
			LOGGER.debug("Found station with code {}: {}", code, station.getName());
			return station;
		} else {
			LOGGER.warn("Station with code {} not found", code);
			throw new StationNotFoundException("Station with code " + code + " not found");
		}
	}
	
	  public List<StationDTO> createStations(List<StationDTO> stationList) {
	        List<StationDTO> createdStations = new ArrayList<>();
	        
	        for (StationDTO stationDTO : stationList) {
	            Station station = new Station();
	            station.setName(stationDTO.getName());
	            station.setStationCode(stationDTO.getStationCode());
	            station.setAddress(stationDTO.getAddress());
	            station.setLatitude(stationDTO.getLatitude());
	            station.setLongitude(stationDTO.getLongitude());
	            
	            stationRepository.save(station);
	            
	            createdStations.add(new StationDTO(station));
	            
	        }
	        
	        return createdStations;
	    }
	  
	  public StationDTO getStationById(Long id) {
	        Optional<Station> optionalStation = stationRepository.findById(id);
	        if (!optionalStation.isPresent()) {
	            throw new ResourceNotFoundException("Station not found with id: " + id);
	        }
	        
	        Station station = optionalStation.get();
	        return new StationDTO(station);
	    }
	    
	    public StationDTO updateStation(Long id, StationDTO stationDTO) {
	        Optional<Station> optionalStation = stationRepository.findById(id);
	        if (!optionalStation.isPresent()) {
	            throw new ResourceNotFoundException("Station not found with id: " + id);
	        }
	        
	        Station station = optionalStation.get();
	        station.setName(stationDTO.getName());
	        station.setStationCode(stationDTO.getStationCode());
	        station.setAddress(stationDTO.getAddress());
	        station.setLatitude(stationDTO.getLatitude());
	        station.setLongitude(stationDTO.getLongitude());
	        
	        stationRepository.save(station);
	        return new StationDTO(station);
	    }
	    
	    public void deleteStation(Long id) {
	        Optional<Station> optionalStation = stationRepository.findById(id);
	        if (!optionalStation.isPresent()) {
	            throw new ResourceNotFoundException("Station not found with id: " + id);
	        }
	        
	        Station station = optionalStation.get();
	        stationRepository.delete(station);
	    }
	}


