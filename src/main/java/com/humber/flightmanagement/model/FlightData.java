package com.humber.flightmanagement.model;

import lombok.Data;

@Data
public class FlightData {

	private String flight_date;
	private Departure departure;
	private Arrival arrival;
	private Airline airline;
	private Flight flight;
}
