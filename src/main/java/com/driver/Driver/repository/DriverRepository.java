package com.driver.Driver.repository;

import java.util.Date;
import java.util.List;

import com.driver.Driver.model.Driver;


/**
 * The interface indicating all doable operations on the {@linkplain Driver} entity.
 * 
 * @author ben-malik
 */
public interface DriverRepository {

    /**
     * Finds all drivers available in the database.
     * @return a list of drivers.
     */
    List<Driver> findAll();

    /**
     * Saves the given new driver to the database.
     * @param newDriver The new driver to be saved.
     * @return The newly saved driver.
     */
    Driver saveDriver(Driver newDriver);

    /**
     * Grabs all drivers created after the given date.
     * @param date The date from which to start the search of the drivers.
     * @return A list of drivers having been created right after the given date.
     */
    List<Driver> getDriversCreatedAfter(Date date);

}
