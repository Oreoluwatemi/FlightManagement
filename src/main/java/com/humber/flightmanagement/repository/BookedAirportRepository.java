package com.humber.flightmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.BookedAirport;

@Repository
public interface BookedAirportRepository extends JpaRepository<BookedAirport, String> {

}
