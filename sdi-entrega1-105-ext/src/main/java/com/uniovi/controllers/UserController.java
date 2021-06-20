package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validators.LogInValidator;
import com.uniovi.validators.SignUpValidator;




@Controller
public class UserController {
	
	
	
	@Autowired 
	private UserService usersService;
	
	@Autowired
	private SignUpValidator signUpValidator;
	
	@Autowired
	private LogInValidator logInValidator;
	
	@Autowired
	private SecurityService securityService;
	
	
	@Autowired
	private HttpSession httpSession;
	


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {

		signUpValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}

		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPassword2());
		httpSession.setAttribute("user", user);
		return "redirect:home";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/myLogin", method = RequestMethod.GET)
	public String customLogin() {
		return "redirect:/login";
	}


	@RequestMapping(value = "/myLogin", method = RequestMethod.POST)
	public String customLogin(User user, BindingResult result) {

		logInValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login";
		}

		httpSession.setAttribute("user", usersService.getUserByEmail(user.getEmail()));
		securityService.autoLogin(user.getEmail(), user.getPassword());
		return "redirect:/home";

	}
	
}
