package com.wittybrains.busbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.ConductorDTO;
import com.wittybrains.busbookingsystem.model.Conductor;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.UserRepository;
import com.wittybrains.busbookingsystem.service.BusService;
import com.wittybrains.busbookingsystem.service.ConductorService;
import com.wittybrains.busbookingsystem.service.UserService;

@RestController
@RequestMapping("/conductors")
public class ConductorController {
    @Autowired
    private ConductorService conductorService;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Conductor> createConductor(@RequestBody ConductorDTO conductorDTO) {
        Conductor conductor = conductorService.createConductor(conductorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
    }

    @GetMapping
    public ResponseEntity<List<Conductor>> getAllConductors() {
        List<Conductor> conductors = conductorService.getAllConductors();
        return ResponseEntity.ok().body(conductors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conductor> getConductorById(@PathVariable Long id) {
        Conductor conductor = conductorService.getConductorById(id);
        return ResponseEntity.ok().body(conductor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conductor> updateConductor(@PathVariable Long id, @RequestBody ConductorDTO conductorDTO) {
        Conductor conductor = conductorService.getConductorById(id);
        if (conductor == null) {
            return ResponseEntity.notFound().build();
        }
        conductor.setName(conductorDTO.getName());
        conductor.setBus(busRepository.findById(conductorDTO.getBusDTO().getId())
                .orElseThrow());
        
        conductor.setUser(userRepository.findById(conductorDTO.getUserDTO().getId())
                .orElseThrow());

        conductor = conductorService.updateConductor(id,conductorDTO);
        return ResponseEntity.ok(conductor);
    }
}

