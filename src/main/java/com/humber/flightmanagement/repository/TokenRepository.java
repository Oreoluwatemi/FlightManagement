package com.humber.flightmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.flightmanagement.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String>{

	Optional<Token> findByToken(String token);
}
