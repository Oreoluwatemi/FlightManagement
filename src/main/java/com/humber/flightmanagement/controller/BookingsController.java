package com.humber.flightmanagement.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.Bookings;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.model.view.BookingView;
import com.humber.flightmanagement.model.view.SearchOneWay;
import com.humber.flightmanagement.service.BookedAirlineService;
import com.humber.flightmanagement.service.BookedAirportService;
import com.humber.flightmanagement.service.BookingsService;
import com.humber.flightmanagement.service.UserService;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@Controller
public class BookingsController {

	@Autowired
	BookedAirportService bookedAirportService;
	
	@Autowired
	BookingsService bookingsService;
	
	@Autowired
	BookedAirlineService bookedAirlineService;
	
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LogManager.getLogger(FlightController.class);

	
	@GetMapping("/book")
	public String bookPage(Model model, SearchOneWay search){
		
		BookedAirline bookedAirline = new BookedAirline();
		  model.addAttribute("airports", bookedAirportService.getAllAirport());
		  model.addAttribute("flights", bookedAirline);
        return "book";
   }
	
	@PostMapping("/booking")
	public String editPostFlight(Model model, SearchOneWay search) {
		 model.addAttribute("airports", bookedAirportService.getAllAirport());
		model.addAttribute("flights", bookingsService.getOneWayFlights(search));

		LOGGER.info("User searched for flights");
		return "book";
	}
	
	@GetMapping("/viewflight/{id}")
	public String viewFlight(@PathVariable String id, Model model, Principal principal){
      BookedAirline flightsAirline = bookedAirlineService.findAirline(id);
		Optional<User> user = userService.getUser(principal.getName());
		
		model.addAttribute("user", user.get());
		model.addAttribute("flight", flightsAirline);
        return "reserve";
   }
	
	@PostMapping("/bookflight")
	public String bookFlight(BookingView bookings ) {

		System.out.print(bookings);
	bookingsService.createBooking(bookings);
	LOGGER.info("User booked a flight searched for flights");
		return "redirect:/book";
	}
	
	@GetMapping("/allbookings")
	public String allBookings(Model model, Principal principal){
		List<Bookings> bookings = bookingsService.getBooking(principal.getName());
		List<BookedAirline> bookedAirline = bookingsService.getBookedAirline(principal.getName());
		model.addAttribute("bookings", bookings);
		System.out.println(bookings);
		System.out.println(bookedAirline);
        return "userbookings";
   }
	
	@GetMapping("/userbooking/{id}")
	public String bookingDetail(@PathVariable String id, Model model, Principal principal){
     Bookings booking = bookingsService.getBookingDetail(id);
     
     model.addAttribute("flight", booking);
     return "detail";
   }
	
	@GetMapping("/deletebooking/{id}")
	public String deleteBooking(@PathVariable String id) {
		bookingsService.deleteBooking(id);
		LOGGER.info("Booking deleted");
		return "redirect:/allbookings";
	}
}
