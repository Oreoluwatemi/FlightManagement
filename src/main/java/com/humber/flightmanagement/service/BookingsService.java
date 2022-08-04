package com.humber.flightmanagement.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.Bookings;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.model.UserBookingInput;
import com.humber.flightmanagement.model.view.SearchOneWay;
import com.humber.flightmanagement.model.view.SearchTwoWay;
import com.humber.flightmanagement.repository.BookedAirlineRepository;
import com.humber.flightmanagement.repository.BookingsRepoository;
import com.humber.flightmanagement.repository.UserRepository;


@Service
public class BookingsService {

	@Autowired
	BookingsRepoository bookingsRepoository;
	
	@Autowired
	BookedAirlineRepository bookedAirlineRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void createBooking(UserBookingInput bookings) {
		Optional<BookedAirline> bookedAirline = bookedAirlineRepository.findByFlight_number(bookings.getFlight_number());
		
		Optional<User> user = userRepository.findByEmail(bookings.getUser());
		
		if(bookedAirline.isPresent() && user.isPresent()) {
			Bookings newBookings = new Bookings();
			newBookings.setBookingid(bookings.getBookingid());
			newBookings.setSeat(bookings.getSeat());
			newBookings.setNo_of_seats(bookings.getNo_of_seats());
			newBookings.setUser(user.get());
			newBookings.setBooked_airline(bookedAirline.get());
			
			bookingsRepoository.save(newBookings);
		}		
	}
	
	public List getBooking(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent()) {
		 List userBookings = bookingsRepoository.findByUser(user.get());
		 return userBookings;
		}
		
		return null;
	}
	
	public List getAllBookings() {
		return (List) bookingsRepoository.findAll();
	}
	
	public void editBooking(UserBookingInput bookings) {
		Optional<BookedAirline> bookedAirline = bookedAirlineRepository.findByFlight_number(bookings.getFlight_number());
		
		Optional<User> user = userRepository.findByEmail(bookings.getUser());
		
		if(bookedAirline.isPresent() && user.isPresent()) {
			Bookings newBookings = new Bookings();
			newBookings.setBookingid(bookings.getBookingid());
			newBookings.setSeat(bookings.getSeat());
			newBookings.setNo_of_seats(bookings.getNo_of_seats());
			newBookings.setUser(user.get());
			newBookings.setBooked_airline(bookedAirline.get());
			
			bookingsRepoository.save(newBookings);
		}		
	}
	
	public void deleteBooking(String id) {
		bookingsRepoository.deleteById(id);
	}
	
	public List getOneWayFlights(SearchOneWay search) {
		List<BookedAirline> flightList = (List<BookedAirline>) bookedAirlineRepository.findAll();
		
		List availableList = (List) new ArrayList<BookedAirline>();
		
		for (BookedAirline b : flightList) {
			if(search.getDeparture_airport().equals(b.getDeparture_airport()) &&
					search.getArrival_airport().equals(b.getArrival_airport()) &&
					search.getFlight_date().equals(b.getFlight_date())) {
				availableList.add(b);
			}
		}
		
		return availableList;
	}
	
	public List getTwoWayFlights(SearchTwoWay search) {
		List<BookedAirline> flightList = (List<BookedAirline>) bookedAirlineRepository.findAll();
		
		List availableList = (List) new ArrayList<BookedAirline>();
		
		for (BookedAirline b : flightList) {
			if(search.getDeparture_airport().equals(b.getDeparture_airport()) &&
					search.getArrival_airport().equals(b.getArrival_airport()) &&
					search.getFlight_date().equals(b.getFlight_date())) {
				availableList.add(b);
			}
		}
		
		return availableList;
	}
}
