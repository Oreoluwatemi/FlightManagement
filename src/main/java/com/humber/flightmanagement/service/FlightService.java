package com.humber.flightmanagement.service;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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



@Service
public class FlightService {

	@Autowired
	RestTemplate restTemplate;
	
	public List<AirportView> getAllAirports() {
		 Airport airports = restTemplate.getForObject("http://api.aviationstack.com/v1/airports?access_key=a024601d311500a71b205757c8baaca2",
		            Airport.class);
		
		 List<AirportData> datas = new ArrayList<AirportData>();
		 for(int i = 0; i < airports.getData().length; i++) {
			 datas.add(airports.getData()[i]);
		 }
		
		// System.out.print(datas);
		 List<AirportView> airportList = new ArrayList<AirportView>();
		 for (AirportData a  : datas) {
			 AirportView newAirport = new AirportView();
			 newAirport.setAirport_name(a.getAirport_name());
			 newAirport.setCountry_name(a.getCountry_name());
			 newAirport.setTimezone(a.getTimezone());
	
			 airportList.add(newAirport);
		}
		 return airportList;
	}
	
	public AirportView getAirport(String name) {
		 Airport airports = restTemplate.getForObject("http://api.aviationstack.com/v1/airports?access_key=a024601d311500a71b205757c8baaca2",
		            Airport.class);
		 List<AirportData> datas = new ArrayList<AirportData>();
		 for(int i = 0; i < airports.getData().length; i++) {
			 datas.add(airports.getData()[i]);
		 }
		 
		 for (AirportData a  : datas) {
			 if(name.equals(a.getAirport_name())) {
			 AirportView newAirport = new AirportView();
			 newAirport.setAirport_name(a.getAirport_name());
			 newAirport.setCountry_name(a.getCountry_name());
			 newAirport.setTimezone(a.getTimezone());
	
			 return newAirport;
			 }
		}
		return null;
	}
	
//	public FlightView getFlight(String number) {
//		Flights flights = restTemplate.getForObject("http://api.aviationstack.com/v1/flights?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
//	            Flights.class);
//		
//		
//		List<FlightView> flightList =  new ArrayList<FlightView>();
//		FlightData[] datas = flights.getData();
//		for (FlightData f : datas) {
//			if(number.equals(f.getFlight().getNumber())) {
//			FlightView newFlight = new FlightView();
//			newFlight.setArrival_airport(f.getArrival().getAirport());
//			newFlight.setArrival_scheduled(f.getArrival().getScheduled());
//			newFlight.setArrival_terminal(f.getArrival().getTerminal());
//			newFlight.setAirline(f.getAirline().getName());
//			newFlight.setDeparture_airport(f.getDeparture().getAirport());
//			newFlight.setDeparture_scheduled(f.getDeparture().getScheduled());
//			newFlight.setDeparture_terminal(f.getDeparture().getTerminal());
//			newFlight.setFlight_date(f.getFlight_date());
//			newFlight.setFlight_number(f.getFlight().getNumber());
//			
//			return newFlight;
//			}
//			
//			
//		}
//		
//		return null;
//		
//	}
//	public List<FlightView> getAllFlights() {
//		Flights flights = restTemplate.getForObject("http://api.aviationstack.com/v1/flights?access_key=adfcce409ba3c0a43a457d3c9cd5c50b",
//	            Flights.class);
//		
//		
//		List<FlightView> flightList =  new ArrayList<FlightView>();
//		FlightData[] datas = flights.getData();
//		for (FlightData f : datas) {
//			FlightView newFlight = new FlightView();
//			newFlight.setArrival_airport(f.getArrival().getAirport());
//			newFlight.setArrival_scheduled(f.getArrival().getScheduled());
//			newFlight.setArrival_terminal(f.getArrival().getTerminal());
//			newFlight.setAirline(f.getAirline().getName());
//			newFlight.setDeparture_airport(f.getDeparture().getAirport());
//			newFlight.setDeparture_scheduled(f.getDeparture().getScheduled());
//			newFlight.setDeparture_terminal(f.getDeparture().getTerminal());
//			newFlight.setFlight_date(f.getFlight_date());
//			newFlight.setFlight_number(f.getFlight().getNumber());
//			
//			flightList.add(newFlight);
//		}
//		
//		return flightList;
//		
//	}
}
