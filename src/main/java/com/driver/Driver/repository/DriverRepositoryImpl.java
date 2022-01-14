package com.driver.Driver.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.driver.Driver.exception.DriverAlreadyExistException;
import com.driver.Driver.model.Driver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

/**
 * An implementation to the {@linkplain DriverRepository} interface.
 * 
 * @author ben-malik
 */
@Repository("driverRepositoryImpl")
public class DriverRepositoryImpl implements DriverRepository {

    private boolean writeToFileFlag = true; // True when available to use and false otherwise.

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    Logger logger = Logger.getLogger(DriverRepositoryImpl.class.getName());

    @Override
    public List<Driver> findAll() {
        return getDriversDB();
    }

    @Override
    public Driver saveDriver(Driver newDriver) {
        return saveDriverToDB(newDriver);
    }

    @Override
    public List<Driver> getDriversCreatedAfter(Date date) {
        List<Driver> drivers = findAll();
        return drivers.stream().filter(a -> a.getCreatedAt().after(date)).collect(Collectors.toList());
    }

    /**
     * A helper method to grab all the drivers available in the db flat file.
     * 
     * @return A list of {@link Driver}
     */
    private List<Driver> getDriversDB() {
        logger.info("Reading of database file started ...");

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Driver>> typeReference = new TypeReference<List<Driver>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/drivers.json");
        List<Driver> drivers = new ArrayList<>();
        try {
            drivers = mapper.readValue(inputStream, typeReference);
            for (Driver d : drivers)
                d.setCreatedAt(formatDate(d.getCreatedAt()));
            logger.info("Reading of drivers from database done!");;
        } catch (IOException e) {
           logger.severe("Unable to read drivers: " + e.getMessage());
        }

        return drivers;
    }

    /**
     * A helper method to save a given driver to the database.
     * @param newDriver the new driver to be saved.
     * @return The newly saved driver.
     */
    private Driver saveDriverToDB(Driver newDriver) {

        List<Driver> drivers = findAll();

        newDriver.setDateOfBirth(formatDate(newDriver.getDateOfBirth()));
        newDriver.setCreatedAt(formatDate(newDriver.getCreatedAt()));
        newDriver.setId(Long.valueOf(drivers.size()));
        
        if (drivers.contains(newDriver)) {
            throw new DriverAlreadyExistException(newDriver.getFirstName(), newDriver.getFirstName());
        }
        drivers.add(newDriver);

        acquireFlag();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(
                getClass().getClassLoader().getResource("db/drivers.json").getFile());
        try {
            logger.info("Saving of driver with name: " + newDriver.getFirstName() + " " + newDriver.getLastName() + " started...");
            mapper.writeValue(file, drivers);
            logger.info("Saving completed!");
        } catch (IOException e) {
            logger.warning(e.getMessage());;
        }

        releaseFlag();
        return newDriver;
    }

    /**
     * Makes the flags unavailable for use to others if the flag itself isn't being
     * used yet.
     */
    private void acquireFlag() {
        if (writeToFileFlag) {
            this.writeToFileFlag = false;
            logger.info("Flag to write to the db successfully acquired!");
        }
        else {
            logger.warning("Flag not available!");
        }
    }

    /**
     * Releases the flag.
     */
    private void releaseFlag() {
        this.writeToFileFlag = true;
        logger.info("Flag released and available for use!");
    }

    /**
     * A helper method to fromate a given date.
     * 
     * @param date The date to be formatted.
     * @return The formatted version of the given date
     */
    private Date formatDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        String output = "";
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        output = year + "-" + month + "-" + day;

        try {
            return SIMPLE_DATE_FORMAT.parse(output);
        } catch (ParseException e) {
            logger.warning(e.getMessage());;
            return null;
        }
    }

}
