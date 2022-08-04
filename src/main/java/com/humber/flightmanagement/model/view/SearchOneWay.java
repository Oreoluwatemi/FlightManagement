package com.humber.flightmanagement.model.view;

import lombok.Data;

@Data
public class SearchOneWay {

	private String departure_airport;
	private String arrival_airport;
	private String flight_date;

}
