package com.driver.Driver.exception;

/**
 * An exception when the given driver already exist.
 * 
 * @author ben-malik
 */
public class DriverAlreadyExistException extends RuntimeException{
    public DriverAlreadyExistException(String firstName, String lastName) {
        super("The driver with name: " + firstName + ", Last Name: " + lastName + " already exist");
    }
}
