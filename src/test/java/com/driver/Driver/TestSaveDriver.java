package com.driver.Driver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.driver.Driver.model.Driver;
import com.driver.Driver.repository.DriverRepository;
import com.driver.Driver.repository.DriverRepositoryImpl;
import com.driver.Driver.service.DriverManager;
import com.driver.Driver.service.DriverManagerImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A unit test for the method {@linkplain DriverManager#saveDriver(newDriver)}
 * 
 * @author ben-malik
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSaveDriver {

    @Mock
    private DriverRepository driverRepository = new DriverRepositoryImpl();

    @Spy // mock it partially
    @InjectMocks
    private DriverManager driverManager = new DriverManagerImpl();


    @Test
    public void ensureDriverIsSavedToTheDatabase() {
        //Data Statment
       Driver driver = generateDriver(1L, "Oyku", "Celik", "2000-03-13");

       // When statment
        when(driverRepository.saveDriver(driver)).thenReturn(driver);
        
        // Call statment 
        Driver savedDriver = driverManager.createDriver(driver);
        
        // Asserstion statment 
        Assert.assertEquals(driver, savedDriver);
        verify(driverRepository, times(0)).findAll();
    }

    /**
     * A helper method to generate a driver with the given information.
     * @param id The id of the driver
     * @param firstName The first name of the driver    
     * @param lastName The last name of the driver.
     * @param dateOfBirth The date of birth of the driver.
     * @return A {@linkplain Driver} object. 
     */
    private Driver generateDriver(Long id, String firstName, String lastName, String dateOfBirth) {
        Driver driver = new Driver();
        Date currentDate = new Date();
        driver.setCreatedAt(currentDate);
        try {
            driver.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        driver.setFirstName("Ben Malik");
        driver.setLastName("Tchamalam");

        return driver;
    }
}
