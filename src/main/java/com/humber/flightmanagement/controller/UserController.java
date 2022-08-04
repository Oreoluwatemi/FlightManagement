package com.humber.flightmanagement.controller;


import java.security.Principal;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.flightmanagement.model.Login;
import com.humber.flightmanagement.model.Token;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.security.WebSecurityConfig;
import com.humber.flightmanagement.service.TokenService;
import com.humber.flightmanagement.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	TokenService tokenService;

	Authentication authentication;
	
	@GetMapping("/sign-in")
	public String signInPage() {

		return "sign-in";
	}

	@GetMapping("/home")
    public String welcome(Principal principal, Model model) {
		model.addAttribute("name", principal.getName() );
		
		UserDetails user = userService.loadUserByUsername(principal.getName());
		System.out.print(user.getAuthorities().toString());
		if(user.getAuthorities().toString().equals("USER")) {
			return "welcome";
		}
		else if(user.getAuthorities().toString().equals("ADMIN")) {
			return "adminhome";
		}
		return "welcome";
    }
	@GetMapping("/sign-up")
	public String signUpPage(User user) {

		return "sign-up";
	}

	@PostMapping("/sign-up")
	public String signUp(User user) {

		userService.registerUser(user);

		return "redirect:/sign-in";
	}
	
	@GetMapping("/admin/sign-up")
	public String signUpAdminPage(User user) {

		return "admin-signup";
	}

	@PostMapping("/admin/sign-up")
	public String signUpAdmin(User user) {

		userService.registerUserAdmin(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	public String confirmMail(@RequestParam("token") String token) {

		Optional<Token> tokenConfirmation = tokenService.findByToken(token);

	
		tokenConfirmation.ifPresent(userService::confirmNewUser);

		return "redirect:/sign-in";
	}

	
}
