package com.humber.flightmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.BookedAirport;
import com.humber.flightmanagement.repository.BookedAirlineRepository;
import com.humber.flightmanagement.repository.BookedAirportRepository;

@Service
public class BookedAirlineService {

	@Autowired
	BookedAirlineRepository bookedAirlineRepository;

	@Autowired
	BookedAirportRepository bookedAirportRepository;

	//save a flight to the database
	public void registerAirline(BookedAirline airline) {

		BookedAirline newAirline = new BookedAirline();
		newAirline.setId(UUID.randomUUID().toString());
		newAirline.setAirline(airline.getAirline());
		newAirline.setArrival_airport(airline.getArrival_airport());
		newAirline.setArrival_gate(airline.getArrival_gate());
		newAirline.setArrival_scheduled(airline.getArrival_scheduled());
		newAirline.setArrival_terminal(airline.getArrival_terminal());
		newAirline.setBusiness_capacity(airline.getBusiness_capacity());
		newAirline.setDeparture_airport(airline.getDeparture_airport());
		newAirline.setDeparture_gate(airline.getDeparture_gate());
		newAirline.setDeparture_scheduled(airline.getDeparture_scheduled());
		newAirline.setDeparture_terminal(airline.getDeparture_terminal());
		newAirline.setEconomy_capacity(airline.getEconomy_capacity());
		newAirline.setFirstclass_capacity(airline.getFirstclass_capacity());
		newAirline.setFlight_date(airline.getFlight_date());
		newAirline.setFlight_number(airline.getFlight_number());

		bookedAirlineRepository.save(newAirline);
	}

	//get all flights from the database
	public List<BookedAirline> getAllAirline() {
		return bookedAirlineRepository.findAll();
	}

	//find a flight
	public BookedAirline findAirline(String id) {
		return bookedAirlineRepository.findByFlight_Number(id);
	}

	//edit a flight
	public void editAirline(BookedAirline bookedAirline) {
		BookedAirline airline = bookedAirlineRepository.findByFlight_Number(bookedAirline.getFlight_number());

		airline.setFlight_date(bookedAirline.getFlight_date());
		airline.setDeparture_scheduled(bookedAirline.getDeparture_scheduled());
		airline.setArrival_scheduled(bookedAirline.getArrival_scheduled());
		airline.setEconomy_capacity(bookedAirline.getEconomy_capacity());
		airline.setBusiness_capacity(bookedAirline.getBusiness_capacity());
		airline.setFirstclass_capacity(bookedAirline.getFirstclass_capacity());
		airline.setArrival_terminal(bookedAirline.getArrival_terminal());
		airline.setDeparture_terminal(bookedAirline.getDeparture_terminal());
		airline.setDeparture_gate(bookedAirline.getDeparture_gate());
		airline.setArrival_gate(bookedAirline.getArrival_gate());

		bookedAirlineRepository.save(airline);

	}
}
