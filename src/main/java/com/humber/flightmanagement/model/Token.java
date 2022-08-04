package com.humber.flightmanagement.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Token {

	@Id
	private String id;

	private String token;

	private LocalDate createdat;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
//	public Token(User user) {
//		this.id = UUID.randomUUID().toString();
//		this.user = user;
//		this.createdat = LocalDate.now();
//		this.token = UUID.randomUUID().toString();
//	}

}
