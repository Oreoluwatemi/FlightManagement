package com.humber.flightmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

	private int limit;
	private int offset;
	private int count;
	private int total;
}
