package com.wittybrains.busbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.ConductorDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.Conductor;
import com.wittybrains.busbookingsystem.model.User;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.ConductorRepository;
import com.wittybrains.busbookingsystem.repository.UserRepository;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    public ConductorService(ConductorRepository conductorRepository, BusRepository busRepository, UserRepository userRepository) {
        this.conductorRepository = conductorRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }



	
	public Conductor createConductor(ConductorDTO conductorDTO) {
	    Conductor conductor = new Conductor();
	    conductor.setName(conductorDTO.getName());
	    
	    if (conductorDTO.getBusDTO() != null) {
	        Bus bus = busRepository.findById(conductorDTO.getBusDTO().getId())
	                .orElseThrow(() -> new RuntimeException("Bus not found"));
	        conductor.setBus(bus);
	    }
	    
	    if (conductorDTO.getUserDTO() != null) {
	        User user = userRepository.findById(conductorDTO.getUserDTO().getId())
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        conductor.setUser(user);
	    }
	    
	    return conductorRepository.save(conductor);
	}


	public List<Conductor> getAllConductors() {
		return conductorRepository.findAll();
	}

	public Conductor getConductorById(Long id) {
		return conductorRepository.findById(id).orElseThrow();
	}

	public Conductor updateConductor(Long id, ConductorDTO conductorDTO) {
		Conductor conductor = conductorRepository.findById(id).orElseThrow();

		conductor.setName(conductorDTO.getName());

		Bus bus = busRepository.findById(conductorDTO.getBusDTO().getId()).orElseThrow();
		conductor.setBus(bus);

		User user = userRepository.findById(conductorDTO.getUserDTO().getId()).orElseThrow();
		conductor.setUser(user);

		return conductorRepository.save(conductor);
	}

	public void deleteConductor(Long id) {
		Conductor conductor = conductorRepository.findById(id).orElseThrow();

		conductorRepository.delete(conductor);
	}

}
