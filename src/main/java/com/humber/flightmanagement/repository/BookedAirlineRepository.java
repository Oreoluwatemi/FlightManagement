package com.humber.flightmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.BookedAirline;

@Repository
public interface BookedAirlineRepository extends JpaRepository<BookedAirline, String>{
		Optional<BookedAirline> findByFlight_number(String flight_number);
	
}
