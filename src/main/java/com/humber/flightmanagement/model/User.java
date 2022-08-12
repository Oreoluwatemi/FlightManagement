package com.humber.flightmanagement.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	private String id;

	@NotBlank(message = "FirstName is required")
	private String firstname;
	
	@NotBlank(message = "LastName is required")
	private String lastname;
	
	@NotBlank(message = "Phone number is required")
	private String phoneno;
	
	@NotBlank(message = "Email is required")
	 @Email(message = "Please enter a valid e-mail address")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
	

	@Builder.Default
	private String userrole = "USER";
	
	@Builder.Default
	private Boolean locked = false;

	@Builder.Default
	private Boolean enabled = false;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userrole);
		return Collections.singletonList(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}
