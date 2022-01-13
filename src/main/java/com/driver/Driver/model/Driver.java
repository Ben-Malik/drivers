package com.driver.Driver.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * A class encapsulating all the data about the drivers.
 * @author ben-malik
 */
@AllArgsConstructor
@EqualsAndHashCode
public class Driver {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("created_at")
    private Date createdAt = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", createdAt=" + createdAt + ", firstName=" + firstName +  ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + "]";
    }
    
    
}
