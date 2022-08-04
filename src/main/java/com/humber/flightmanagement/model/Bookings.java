package com.humber.flightmanagement.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
public class Bookings {
	
	
	private String bookingid;
	private String seat;
	private int no_of_seats;
	
	@ManyToMany(targetEntity = BookedAirline.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "bookedairline_id")
	private BookedAirline booked_airline;
	
	@ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	

}
