package com.humber.flightmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import com.humber.flightmanagement.model.Token;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	public void registerUser(User user) {
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		User newUser = new User();
		String userString = user.getEmail();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setEmail(userString);
		newUser.setPhoneno(user.getPhoneno());
		newUser.setPassword(encodedPassword);
		newUser.setEnabled(false);
		newUser.setLocked(false);
		newUser.setUserrole("USER");
		userRepository.save(newUser);
		
		Token token = new Token();
		String tbString = UUID.randomUUID().toString();
		token.setId(UUID.randomUUID().toString());
		token.setUser(newUser);
		token.setCreatedat(LocalDate.now());
		token.setToken(tbString);
		tokenService.saveToken(token);
		
		System.out.println(userString);
		
		System.out.println(tbString);
		sendEMail(userString, tbString);
	}
	
public void registerUserAdmin(User user) {
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		User newUser = new User();
		String userString = user.getEmail();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setEmail(userString);
		newUser.setPhoneno(user.getPhoneno());
		newUser.setPassword(encodedPassword);
		newUser.setEnabled(false);
		newUser.setLocked(false);
		newUser.setUserrole("ADMIN");
		userRepository.save(newUser);
		
		Token token = new Token();
		String tbString = UUID.randomUUID().toString();
		token.setId(UUID.randomUUID().toString());
		token.setUser(newUser);
		token.setCreatedat(LocalDate.now());
		token.setToken(tbString);
		tokenService.saveToken(token);
		
		System.out.println(userString);
		
		System.out.println(tbString);
		sendEMail(userString, tbString);
	}
	
	public void confirmNewUser(Token token) {

		final User user = token.getUser();

		user.setEnabled(true);

		userRepository.save(user);

		tokenService.deleteToken(token.getId());

	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
		return Collections.singletonList(simpleGrantedAuthority);
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email)));
	}
	
	void sendEMail(String userMail, String token) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		System.out.print("Sent email started");
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Mail Confirmation Link!");
		mailMessage.setFrom("temmitayolawal35@gmail.com");
		mailMessage.setText(
				"Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
						+ token);

		System.out.print("Sent email process");
		sendEmailService.sendEmail(mailMessage);
		System.out.print("Sent email ended");
	}
	
//	public void autoLogin(String username, String password) {
//		System.out.println(username);
//        UserDetails userDetails = loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            //logger.debug(String.format("Auto login %s successfully!", username));
//        }
//    }
}
