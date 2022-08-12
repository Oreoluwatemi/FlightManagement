package com.humber.flightmanagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class BookedAirline {

	@Id
	public String id;
	private String flight_date;
	private String departure_airport;
	private String departure_terminal;
	private String departure_gate;
	private String departure_scheduled;
	private String arrival_airport;
	private String arrival_terminal;
	private String arrival_gate;
	private String arrival_scheduled;
	private String airline;
	private String flight_number;
	private int economy_capacity;
	private int business_capacity;
	private int firstclass_capacity;
	
}
