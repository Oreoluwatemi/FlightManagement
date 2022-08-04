package com.humber.flightmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

	private Pagination pagination;
	private AirportData[] data;
	
	
}
