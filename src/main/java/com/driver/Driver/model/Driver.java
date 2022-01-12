package com.driver.Driver.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A class encapsulating all the data about the drivers.
 * @author ben-malik
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Driver {
    
    private Long id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private Date createdAt;

}
