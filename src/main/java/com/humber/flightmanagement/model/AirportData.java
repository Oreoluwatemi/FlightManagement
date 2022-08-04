package com.humber.flightmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportData {

	private String airport_name;
	private String iata_code;
	private String icao_code;
	private String latitude;
	private String longitude;
	private String geoname_id;
	private String timezone;
	private String gmt;
	private String phone_number;
	
	private String country_name;
	private String country_iso2;
	
	private String city_iata_codee;

}
