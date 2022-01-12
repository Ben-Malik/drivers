package com.driver.Driver.service;

import java.util.Date;
import java.util.List;

import com.driver.Driver.model.Driver;

import org.springframework.stereotype.Service;

/**
 * An implementation to the {@link DriverManager} interface.
 * 
 * @author ben-malik
 */
@Service
public class DriverManagerImpl implements DriverManager{

    @Override
    public List<Driver> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Driver> getDriversCreatedAfter(Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Driver createDriver(Driver newDriver) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
