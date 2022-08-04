package com.humber.flightmanagement.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.humber.flightmanagement.model.Airport;
import com.humber.flightmanagement.model.AirportData;
import com.humber.flightmanagement.model.BookedAirport;
import com.humber.flightmanagement.model.FlightData;
import com.humber.flightmanagement.model.Flights;
import com.humber.flightmanagement.model.view.AirportView;
import com.humber.flightmanagement.model.view.FlightView;

import antlr.collections.List;

@Service
public class FlightService {

	@Autowired
	RestTemplate restTemplate;
	
	public List getAllAirports() {
		 Airport airports = restTemplate.getForObject("http://api.aviationstack.com/v1/airports?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
		            Airport.class);
		 AirportView newAirport = new AirportView();
		  
		 List airportList = (List) new ArrayList<AirportView>();
		 for (AirportData a  : airports.getData()) {
			 newAirport.setAirport_name(a.getAirport_name());
			 newAirport.setCountry_name(a.getCountry_name());
			 newAirport.setTimezone(a.getTimezone());
			 
			 airportList.append(newAirport);
		}
		
		 return airportList;
	}
	
	public List getAllFlights() {
		Flights flights = restTemplate.getForObject("http://api.aviationstack.com/v1/flights?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
	            Flights.class);
		
		FlightView newFlight = new FlightView();
		List flightList = (List) new ArrayList<FlightView>();
		
		for (FlightData f : flights.getData()) {
			newFlight.setArrival_airport(f.getArrival().getAirport());
			newFlight.setArrival_scheduled(f.getArrival().getScheduled());
			newFlight.setArrival_terminal(f.getArrival().getTerminal());
			newFlight.setAirline(f.getAirline().getName());
			newFlight.setDeparture_airport(f.getDeparture().getAirport());
			newFlight.setDeparture_scheduled(f.getDeparture().getScheduled());
			newFlight.setDeparture_terminal(f.getDeparture().getTerminal());
			newFlight.setFlight_date(f.getFlight_date());
			newFlight.setFlight_number(f.getFlight().getNumber());
			
			flightList.append(newFlight);
		}
		
		return flightList;
		
	}
}
