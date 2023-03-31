package com.wittybrains.busbookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wittybrains.busbookingsystem.dto.PassengerDTO;
import com.wittybrains.busbookingsystem.model.Passenger;
import com.wittybrains.busbookingsystem.repository.PassengerRepository;

@Service
public class PassengerService {

	private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    
    

    public Passenger createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerDTO.getName());
        passenger.setContactNumber(passengerDTO.getContactNumber());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setPassword(passengerDTO.getPassword());
        passenger.setCreatedOn(LocalDateTime.now());
        passenger.setUpdatedOn(LocalDateTime.now());
        return passengerRepository.save(passenger);
    }
    public Optional<Passenger> getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    public void updatePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

   
    }

    


