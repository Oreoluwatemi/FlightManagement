package com.humber.flightmanagement.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
public class UserBookingInput {

	private String bookingid;
	private String seat;
	private int no_of_seats;
	private String flight_number;
	private String user;
}
