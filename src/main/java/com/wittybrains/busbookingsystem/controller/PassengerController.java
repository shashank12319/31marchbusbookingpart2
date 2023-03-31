package com.wittybrains.busbookingsystem.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.PassengerDTO;
import com.wittybrains.busbookingsystem.model.Passenger;
import com.wittybrains.busbookingsystem.service.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = passengerService.createPassenger(passengerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
    }
    
    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long passengerId) {
        Optional<Passenger> optionalPassenger = passengerService.getPassengerById(passengerId);
        if (optionalPassenger.isPresent()) {
            return ResponseEntity.ok(optionalPassenger.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   

    @PutMapping("/{passengerId}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long passengerId, @RequestBody PassengerDTO passengerDTO) {
        Optional<Passenger> optionalPassenger = passengerService.getPassengerById(passengerId);
        if (optionalPassenger.isPresent()) {
            Passenger passenger = optionalPassenger.get();
            passenger.setName(passengerDTO.getName());
            passenger.setContactNumber(passengerDTO.getContactNumber());
            passenger.setEmail(passengerDTO.getEmail());
            passenger.setPassword(passengerDTO.getPassword());
            passenger.setUpdatedOn(LocalDateTime.now());
            passengerService.updatePassenger(passenger);
            return ResponseEntity.ok(passenger);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        Optional<Passenger> optionalPassenger = passengerService.getPassengerById(passengerId);
        if (optionalPassenger.isPresent()) {
            passengerService.deletePassenger(passengerId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
    
    
