package com.humber.flightmanagement.model;

import lombok.Data;

@Data
public class Departure {

	private String airport;
	private String timezone;
	private String terminal;
	private String gate;
	private String scheduled;
}
