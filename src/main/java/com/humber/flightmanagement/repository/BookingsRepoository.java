package com.humber.flightmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.Bookings;
import com.humber.flightmanagement.model.User;

import antlr.collections.List;

@Repository
public interface BookingsRepoository extends JpaRepository<Bookings, String> {

	List findByUser(User user);
}
