package com.humber.flightmanagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Bookings {
	
	@Id
	private String bookingid;

	private String seat;

	private int no_of_seats;
	
	@OneToOne(targetEntity = BookedAirline.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "bookedairline_id")
	private BookedAirline booked_airline;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	

}
