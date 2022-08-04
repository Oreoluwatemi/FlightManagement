package com.humber.flightmanagement.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.repository.BookedAirlineRepository;

import antlr.collections.List;

@Service
public class BookedAirlineService {

	@Autowired
	BookedAirlineRepository bookedAirlineRepository;
	
	public void registerAirline(BookedAirline airline) {
		
		BookedAirline newAirline = new BookedAirline();
		newAirline.setId(UUID.randomUUID().toString());
		newAirline.setAirline(airline.getAirline());
		newAirline.setArrival_airport(airline.getArrival_airport());
		newAirline.setArrival_gate(airline.getArrival_gate());
		newAirline.setArrival_scheduled(airline.getArrival_scheduled());
		newAirline.setArrival_terminal(airline.getArrival_terminal());
		newAirline.setArrival_timezone(airline.getArrival_timezone());
		newAirline.setBusiness_capacity(airline.getBusiness_capacity());
		newAirline.setDeparture_airport(airline.getDeparture_airport());
		newAirline.setDeparture_gate(airline.getDeparture_gate());
		newAirline.setDeparture_scheduled(airline.getDeparture_scheduled());
		newAirline.setDeparture_terminal(airline.getDeparture_terminal());
		newAirline.setDeparture_timezone(airline.getDeparture_timezone());
		newAirline.setEconomy_capacity(airline.getEconomy_capacity());
		newAirline.setFirstclass_capacity(airline.getFirstclass_capacity());
		newAirline.setFlight_date(airline.getFlight_date());
		newAirline.setFlight_number(airline.getFlight_number());
		
		bookedAirlineRepository.save(newAirline);
	}
	
	public List getAllAirline() {
		return (List) bookedAirlineRepository.findAll();
	}
}
