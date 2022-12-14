package com.humber.flightmanagement.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.Token;
import com.humber.flightmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByEmail(String email);
}
