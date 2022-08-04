package com.humber.flightmanagement.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirport;
import com.humber.flightmanagement.repository.BookedAirportRepository;

import antlr.collections.List;

@Service
public class BookedAirportService {

	@Autowired
	BookedAirportRepository bookedAirportRepository;
	
	public void registerAirport(BookedAirport airport) {
		
		BookedAirport newAirport = new BookedAirport();
		newAirport.setId(UUID.randomUUID().toString());
		newAirport.setAirport_name(airport.getAirport_name());
		newAirport.setCountry_name(airport.getCountry_name());
		newAirport.setTimezone(airport.getTimezone());
		
		bookedAirportRepository.save(newAirport);
	}
	
	public List getAllAirports() {
	  return (List) bookedAirportRepository.findAll();	
	}
}
