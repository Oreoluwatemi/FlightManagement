package com.humber.flightmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BookedAirport {

	@Id
	private String id;
	private String airport_name;
	private String timezone;
	private String country_name;
}
