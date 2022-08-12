package com.humber.flightmanagement.model.view;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.User;

import lombok.Data;

@Data
public class BookingView {


	private String seat;
	 
	private int no_of_seats;
	private String booked_airline;
	private String user_id;
	
}
