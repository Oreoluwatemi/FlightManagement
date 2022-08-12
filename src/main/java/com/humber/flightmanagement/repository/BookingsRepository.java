package com.humber.flightmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.BookedAirline;
import com.humber.flightmanagement.model.Bookings;
import com.humber.flightmanagement.model.User;



@Repository
public interface BookingsRepository extends JpaRepository<Bookings, String> {

	@Query(value = "SELECT * FROM bookings b WHERE b.user_id = :no",nativeQuery=true)
	List<Bookings> findByUser(@Param("no")String userid);
}
