package com.driver.Driver.service;

import java.util.Date;
import java.util.List;

import com.driver.Driver.model.Driver;
import com.driver.Driver.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * An implementation to the {@link DriverManager} interface.
 * 
 * @author ben-malik
 */
@Service("driverManagerImpl")
public class DriverManagerImpl implements DriverManager{

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> getDriversCreatedAfter(Date date) {
        return driverRepository.getDriversCreatedAfter(date);
    }

    @Override
    public Driver createDriver(Driver newDriver) {
        return driverRepository.saveDriver(newDriver);
    }

    @Override
    public Driver getById(Long id) {
        return driverRepository.getById(id);
    }
    
}
