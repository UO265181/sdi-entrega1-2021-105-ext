package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;




@Controller
public class UserController {
	
	
	
	@Autowired 
	private UserService usersService;
	

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {

		//signUpFormValidator.validate(user, result);

		if (result.hasErrors()) {
			return "signup";
		}

		usersService.addUser(user);
		//securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		//httpSession.setAttribute("user", user);
		return "redirect:home";
	}
	
}
