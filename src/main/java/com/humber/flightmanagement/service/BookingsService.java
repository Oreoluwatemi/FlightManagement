package com.humber.flightmanagement.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.Bookings;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.model.UserBookingInput;
import com.humber.flightmanagement.model.view.BookingView;
import com.humber.flightmanagement.model.view.SearchOneWay;
import com.humber.flightmanagement.repository.BookedAirlineRepository;
import com.humber.flightmanagement.repository.BookingsRepository;
import com.humber.flightmanagement.repository.UserRepository;

@Service
public class BookingsService {

	@Autowired
	BookingsRepository bookingsRepoository;

	@Autowired
	BookedAirlineRepository bookedAirlineRepository;

	@Autowired
	UserRepository userRepository;

	// user creates a booking
	public void createBooking(BookingView bookings) {
		// get airline details
		Optional<BookedAirline> bookedAirline = bookedAirlineRepository.findById(bookings.getBooked_airline());

		// get user details
		Optional<User> user = userRepository.findById(bookings.getUser_id());

		// add booking to the database
		if (user.isPresent() && bookedAirline.isPresent()) {
			Bookings newBookings = new Bookings();
			newBookings.setBookingid(UUID.randomUUID().toString());
			newBookings.setSeat(bookings.getSeat());
			newBookings.setNo_of_seats(bookings.getNo_of_seats());
			newBookings.setUser(user.get());
			newBookings.setBooked_airline(bookedAirline.get());

			bookingsRepoository.save(newBookings);
		}
	}

	// get a user bookings
	public List<Bookings> getBooking(String email) {

		// find user details
		Optional<User> user = userRepository.findByEmail(email);

		// find all bookings related to user
		List<Bookings> userBookings = bookingsRepoository.findByUser(user.get().getId());

		return userBookings;
	}

	// get flight details of current user
	public List<BookedAirline> getBookedAirline(String email) {

		// get user details
		Optional<User> user = userRepository.findByEmail(email);

		List<Bookings> userBookings = bookingsRepoository.findByUser(user.get().getId());

		// find all flights details booked by user
		List<BookedAirline> bookedAirlines = new ArrayList<BookedAirline>();
		for (Bookings bookings : userBookings) {

			Optional<BookedAirline> bookedAirline = bookedAirlineRepository
					.findById(bookings.getBooked_airline().getId());
			System.out.println(bookedAirline);
			if (bookedAirline.isPresent()) {
				bookedAirlines.add(bookedAirline.get());
			}
		}
		return bookedAirlines;
	}

	// get all bookings
	public List<Bookings> getAllBookings() {
		return bookingsRepoository.findAll();
	}

	public Bookings getBookingDetail(String id) {
		Optional<Bookings> booking = bookingsRepoository.findById(id);

		return booking.get();
	}

	public void deleteBooking(String id) {
		bookingsRepoository.deleteById(id);
	}

	// search for available flights
	public List<BookedAirline> getOneWayFlights(SearchOneWay search) {
		// get all flights
		List<BookedAirline> flightList = bookedAirlineRepository.findAll();

		List<BookedAirline> availableList = new ArrayList<BookedAirline>();

		// filter flight with airport and date searched by a user
		for (BookedAirline b : flightList) {
			if (search.getDeparture_airport().equals(b.getDeparture_airport())
					&& search.getArrival_airport().equals(b.getArrival_airport())
					&& search.getFlight_date().equals(b.getFlight_date())) {
				availableList.add(b);
			}
		}

		return availableList;
	}

}
