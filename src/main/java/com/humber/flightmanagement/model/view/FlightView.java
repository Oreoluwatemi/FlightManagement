package com.humber.flightmanagement.model.view;

import lombok.Data;

@Data
public class FlightView {
	private String flight_date;
	private String departure_airport;
	private String departure_terminal;
	private String departure_scheduled;
	private String arrival_airport;
	private String arrival_terminal;
	private String arrival_scheduled;
	private String airline;
	private String flight_number;
}
