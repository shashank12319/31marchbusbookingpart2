package com.wittybrains.busbookingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.DriverDTO;
import com.wittybrains.busbookingsystem.model.Driver;
import com.wittybrains.busbookingsystem.repository.DriverRepository;

@Service
public class DriverService {

	private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver createDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setLicenseNumber(driverDTO.getLicenseNumber());

        

        return driverRepository.save(driver);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElseThrow();
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver updateDriver(Long id, DriverDTO driverDTO) {
        Driver driver = getDriverById(id);
        driver.setName(driverDTO.getName());
        driver.setLicenseNumber(driverDTO.getLicenseNumber());
      
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
