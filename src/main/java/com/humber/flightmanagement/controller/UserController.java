package com.humber.flightmanagement.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.Optional;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.flightmanagement.model.Login;
import com.humber.flightmanagement.model.Token;
import com.humber.flightmanagement.model.User;
import com.humber.flightmanagement.security.WebSecurityConfig;
import com.humber.flightmanagement.service.FlightService;
import com.humber.flightmanagement.service.TokenService;
import com.humber.flightmanagement.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	TokenService tokenService;

	@Autowired
	FlightService flightService;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	Authentication authentication;

	@GetMapping("/sign-in")
	public String signInPage() {

		return "sign-in";
	}

	@GetMapping("/home")
	public String welcome(Principal principal, Model model) {
		model.addAttribute("name", principal.getName());

		UserDetails user = userService.loadUserByUsername(principal.getName());
		System.out.print(user.getAuthorities());
		System.out.print("Oreooluwa");
		System.out.print(user.getAuthorities());
		LOGGER.info("User signed in: " + principal.getName() + "  User role: " + user.getAuthorities());
		if (user.getAuthorities().toString().equals("[USER]")) {
			return "welcome";
		} else if (user.getAuthorities().toString().equals("[ADMIN]")) {

			return "adminhome";
		}
		return "welcome";
	}

	@GetMapping("/sign-up")
	public String signUpPage(User user) {

		return "sign-up";
	}

	@PostMapping("/sign-up")
	public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			LOGGER.error("Invalid registration");
			return "sign-up";
		}
		LOGGER.info("User signed up: " + user.getEmail() + "  Confirmation link sent ");
		userService.registerUser(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/admin/sign-up")
	public String signUpAdminPage(User user) {

		return "admin-signup";
	}

	@PostMapping("/admin/sign-up")
	public String signUpAdmin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			LOGGER.error("Invalid registration");
			return "sign-up";
		}
		LOGGER.info("User signed up: " + user.getEmail() + "  Confirmation link sent ");

		userService.registerUserAdmin(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	public String confirmMail(@RequestParam("token") String token) {

		Optional<Token> tokenConfirmation = tokenService.findByToken(token);

		tokenConfirmation.ifPresent(userService::confirmNewUser);

		LOGGER.info("User" + tokenConfirmation.get().getUser().getEmail() + "confirmed email address");
		;
		return "redirect:/sign-in";
	}

}
