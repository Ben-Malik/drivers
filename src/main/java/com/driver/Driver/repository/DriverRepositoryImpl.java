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
import java.util.stream.Collectors;

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

    @Override
    public Driver getById(Long id) {
        List<Driver> drivers = findAll();
        return drivers.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }

    /**
     * A helper method to grab all the drivers available in the db flat file.
     * 
     * @return A list of {@link Driver}
     */
    private List<Driver> getDriversDB() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Driver>> typeReference = new TypeReference<List<Driver>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/drivers.json");
        List<Driver> drivers = new ArrayList<>();
        try {
            drivers = mapper.readValue(inputStream, typeReference);
            for (Driver d : drivers)
                d.setCreatedAt(formatDate(d.getCreatedAt()));
            System.out.println("Reading of Drivers Completed!");
        } catch (IOException e) {
            System.out.println("Unable to read drivers: " + e.getMessage());
        }

        return drivers;
    }

    /**
     * TODO write javadoc
     * 
     * @param newDriver
     * @return
     */
    private Driver saveDriverToDB(Driver newDriver) {
        List<Driver> drivers = getDriversDB();

        newDriver.setDateOfBirth(formatDate(newDriver.getDateOfBirth()));
        newDriver.setCreatedAt(formatDate(newDriver.getCreatedAt()));
        newDriver.setId(Long.valueOf(drivers.size()));
        drivers.add(newDriver);

        acquireFlag();
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(
                getClass().getClassLoader().getResource("db/drivers.json").getFile());
        try {
            mapper.writeValue(file, drivers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        releaseFlag();
        return newDriver;
    }

    /**
     * Makes the flags unavailable for use to others if the flag itself isn't being
     * used yet.
     */
    private void acquireFlag() {
        if (writeToFileFlag)
            this.writeToFileFlag = false;
        else
            throw new IllegalStateException("The flag is currently not avaiable!");
    }

    /**
     * Releases the flag.
     */
    private void releaseFlag() {
        this.writeToFileFlag = true;
    }

    /**
     * TODO write javadoc
     * 
     * @param date
     * @return
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
            e.printStackTrace();
            return null;
        }
    }
}
