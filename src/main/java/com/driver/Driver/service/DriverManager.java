package com.driver.Driver.service;

import java.util.Date;
import java.util.List;

import com.driver.Driver.model.Driver;

/**
 * The interface for the driver service, acting as the bridge between the
 * DriverController and the
 * DriverRepository and reponsible for handling all business logics.
 * 
 * @author ben-malik
 */
public interface DriverManager {

    /**
     * @see DriverRepository#findAll()
     */
    List<Driver> findAll();

    /**
     * @see DriverRepository#getDriversCreatedAfter(Date date)
     */
    List<Driver> getDriversCreatedAfter(Date date);

    /**
     * @see DriverRepository#saveDriver(Driver newDriver)
     */
    Driver createDriver(Driver newDriver);

     /**
     * @see DriverRepository#getById(Long id)
     */
    Driver getById(Long id);

}
