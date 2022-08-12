package com.humber.flightmanagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.view.AirportView;
import com.humber.flightmanagement.model.view.FlightView;
import com.humber.flightmanagement.service.BookedAirlineService;
import com.humber.flightmanagement.service.BookedAirportService;
import com.humber.flightmanagement.service.FlightService;

@Controller
public class FlightController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	BookedAirportService bookedAirportService;
	
	@Autowired
	BookedAirlineService bookedAirlineService;
	
	private static final Logger LOGGER = LogManager.getLogger(FlightController.class);


	@GetMapping("/flights")
	public String getFlightsDetails(Model model){
		model.addAttribute("flights", bookedAirlineService.getAllAirline()); 
       
        return "flights";
   }
	
	@GetMapping("/editflight/{id}")
	public String editFlight(@PathVariable String id, Model model, BookedAirline bookedAirline){
		BookedAirline flightsAirline = bookedAirlineService.findAirline(id);
		
		model.addAttribute("flight", flightsAirline);
       
        return "editflight";
   }
	
	@PostMapping("/admin/editflight")
	public String editPostFlight(BookedAirline flight) {

		bookedAirlineService.editAirline(flight);

		LOGGER.info("flight with number" + flight.getFlight_number() + " edited");
		return "redirect:/flights";
	}
	
	@GetMapping("/addflight")
	public String addFlight(Model model, BookedAirline bookedAirline){
       model.addAttribute("airports", bookedAirportService.getAllAirport());
       
        return "addflight";
   }
	@PostMapping("/admin/addflight")
	public String registerFlight(BookedAirline airline) {

		bookedAirlineService.registerAirline(airline);

		 LOGGER.info("Successfully created a new flight details");
		return "redirect:/flights";
	}
}
