package com.wittybrains.busbookingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.wittybrains.busbookingsystem.dto.BusDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.repository.BusRepository;

@Service
public class BusService {

	private final BusRepository busRepository;
	private final Logger logger = LoggerFactory.getLogger(BusService.class);

	public BusService(BusRepository busRepository) {
		this.busRepository = busRepository;
	}

	public List<BusDTO> createBuses(List<BusDTO> busList) {
		List<BusDTO> createdBuses = new ArrayList<>();

		for (BusDTO busDTO : busList) {
			Bus bus = new Bus();
			
			bus.setNumber(busDTO.getNumber());
		
			bus.setCapacity(busDTO.getCapacity());
			bus.setType(busDTO.getType());
			busRepository.save(bus);

			createdBuses.add(new BusDTO(bus));
			logger.info("New bus created: {}", busDTO);
		}

		return createdBuses;
	}
	public BusDTO updateBus(Long id, BusDTO busDTO) {
		Bus bus = busRepository.findById(id)
				.orElseThrow();

		bus.setNumber(busDTO.getNumber());
		bus.setCapacity(busDTO.getCapacity());
		bus.setType(busDTO.getType());

		busRepository.save(bus);

		return new BusDTO(bus);
	}
	public BusDTO getBusById(Long id) {
		Bus bus = busRepository.findById(id)
				.orElseThrow();
		return new BusDTO(bus);
	}

	public void deleteBus(Long id) {
		Bus bus = busRepository.findById(id)
				.orElseThrow();

		busRepository.delete(bus);
		logger.info("Bus with id {} deleted", id);
	}

}
