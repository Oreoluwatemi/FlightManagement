package com.humber.flightmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirport;
import com.humber.flightmanagement.model.view.AirportView;
import com.humber.flightmanagement.repository.BookedAirportRepository;

@Service
public class BookedAirportService {

	@Autowired
	BookedAirportRepository bookedAirportRepository;

	// asve airport to the database
	public void registerAirport(AirportView airport) {

		BookedAirport newAirport = new BookedAirport();
		newAirport.setId(UUID.randomUUID().toString());
		newAirport.setAirport_name(airport.getAirport_name());
		newAirport.setCountry_name(airport.getCountry_name());
		newAirport.setTimezone(airport.getTimezone());

		bookedAirportRepository.save(newAirport);
	}

	// get all airports
	public List<BookedAirport> getAllAirport() {
		return bookedAirportRepository.findAll();
	}
}
