package com.humber.flightmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.humber.flightmanagement.model.Airport;
import com.humber.flightmanagement.model.Flights;
import com.humber.flightmanagement.model.Login;

import antlr.collections.List;

@Controller
public class AirportController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/airports")
	public String getFromUrl() throws JsonProcessingException {
        Airport airportsList = restTemplate.getForObject("http://api.aviationstack.com/v1/airports?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
            Airport.class);
        System.out.println(airportsList);
        return "success";
   }
	
	@GetMapping("/flights")
	public String getFlightsDetails() throws JsonProcessingException {
        Flights flights = restTemplate.getForObject("http://api.aviationstack.com/v1/flights?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
            Flights.class);
        System.out.println(flights);
        return "success";
   }
}
