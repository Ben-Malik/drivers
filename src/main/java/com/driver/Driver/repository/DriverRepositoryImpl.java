package com.driver.Driver.repository;

import java.util.Date;
import java.util.List;

import com.driver.Driver.model.Driver;

import org.springframework.stereotype.Repository;

/** 
 * An implementation to the {@linkplain DriverRepository} interface.
 * 
 * @author ben-malik
 */
@Repository("driverRepositoryImpl")
public class DriverRepositoryImpl implements DriverRepository{

    @Override
    public List<Driver> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Driver saveDriver(Driver newDriver) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Driver> getDriversCreatedAfter(Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Driver getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
