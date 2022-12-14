package com.humber.flightmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.BookedAirline;

@Repository
public interface BookedAirlineRepository extends JpaRepository<BookedAirline, String>{
		
	@Query(value = "SELECT * FROM booked_airline b WHERE b.flight_number= :no",nativeQuery=true)
	BookedAirline findByFlight_Number(@Param("no")String flight_number);
	
}
