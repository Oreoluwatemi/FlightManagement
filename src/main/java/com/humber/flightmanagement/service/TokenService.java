package com.humber.flightmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.flightmanagement.model.Token;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	
	public void saveToken(Token token) {
		tokenRepository.save(token);
	}
	
	public void deleteToken(String id) {
		tokenRepository.deleteById(id);
	}
	
	public Optional<Token> findByToken(String token) {
		return tokenRepository.findByToken(token);
	}
	
}
