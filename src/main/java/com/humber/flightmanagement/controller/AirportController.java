package com.humber.flightmanagement.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.humber.flightmanagement.model.Airport;
import com.humber.flightmanagement.model.Flights;
import com.humber.flightmanagement.model.Login;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.model.view.AirportView;
import com.humber.flightmanagement.service.BookedAirportService;
import com.humber.flightmanagement.service.FlightService;

import antlr.collections.List;

@Controller
public class AirportController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	BookedAirportService bookedAirportService;
	
	private static final Logger LOGGER = LogManager.getLogger(FlightController.class);

	
	@GetMapping("/airports")
	public String getAirports(Model model) {
		model.addAttribute("airports", flightService.getAllAirports());
		LOGGER.info("Fetched arport data frommaviation flights");
	    return "airports";
	}
	
	@GetMapping("/addairport/{id}")
	public String addAirport(@PathVariable String id, Model model, AirportView airport){
		AirportView airportView = flightService.getAirport(id);
		
		model.addAttribute("airport", airportView);

       
        return "addairport";
   }
	
	@PostMapping("/admin/addairport")
	public String registerAirport(AirportView airportView) {

		bookedAirportService.registerAirport(airportView);
		
		LOGGER.info("Saved airport to the database");

		return "redirect:/airports";
	}

}
