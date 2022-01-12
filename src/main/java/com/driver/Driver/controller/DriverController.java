package com.driver.Driver.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.driver.Driver.model.Driver;
import com.driver.Driver.service.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DriverController {

    @Autowired
    private DriverManager driverManager;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("drivers", driverManager.findAll());
        return "index";
    }

    @RequestMapping(value = "/driver/add", method = { RequestMethod.GET})
    public String addDriver(@ModelAttribute Driver driver, BindingResult result, Model model) {
        model.addAttribute("driver", new Driver());
        
        return "driverForm";
    }
    
    @RequestMapping(value = "/driver/save", method = RequestMethod.POST)
    public String saveDriver(@Validated @ModelAttribute("driver") Driver driver, HttpServletRequest request) {
        if (driver.getId() != null) {
			Driver oldDriver = driverManager.getById(driver.getId());
			oldDriver = driver;
			driverManager.createDriver(oldDriver);
			return "redirect:index";
		}
	
		driverManager.createDriver(driver);	
		return "redirect:index";
    }
	
    @RequestMapping(value = "/drivers/{createdAt}", method = RequestMethod.GET)
	public String driversCreatedAfter( @PathVariable("createdAt") Date date, Model model) {
		model.addAttribute("drivers", driverManager.getDriversCreatedAfter(date));
		return "index";
	}
	
    


    
}
