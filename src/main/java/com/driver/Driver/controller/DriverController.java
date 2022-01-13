package com.driver.Driver.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.driver.Driver.model.Driver;
import com.driver.Driver.service.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DriverController {

    @Autowired
    private DriverManager driverManager;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("drivers", driverManager.findAll());
        model.addAttribute("newDriver", new Driver());
        return "index";
    }

    @RequestMapping(value = "/driver/create", method = RequestMethod.POST)
    public String saveDriver(@Validated @ModelAttribute("driver") Driver driver, HttpServletRequest request, Model model) {
        driverManager.createDriver(driver);
        model.addAttribute("drivers", driverManager.findAll());
        model.addAttribute("newDriver", getNextDriver(driverManager.findAll().size()));
        return "index";
    }

    @RequestMapping(value = "/drivers/{byDate}", method = RequestMethod.GET)
    public @ResponseBody List<Driver> driversCreatedAfter(@PathVariable("byDate") String date, Model model) {
        List<Driver> drivers = new ArrayList<>();
        try {
            drivers = driverManager.getDriversCreatedAfter(SIMPLE_DATE_FORMAT.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public @ResponseBody List<Driver> getAllDrivers(Model model) {
        return driverManager.findAll();
    }

    private Driver getNextDriver(int nextId) {
        Driver driver = new Driver();
        driver.setId(Long.valueOf(nextId));
        return driver;
    }
}
