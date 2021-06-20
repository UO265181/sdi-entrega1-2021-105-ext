package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;

@Component
public class LogInValidator implements Validator {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.vacio");

		if (!user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {

			if (userService.getUserByEmail(user.getEmail()) == null) {
				errors.rejectValue("email", "Error.login.email");
			}

			else if (!securityService.correctUser(user.getEmail(), user.getPassword())) {
				errors.rejectValue("password", "Error.login.contrasena");
			}
		}
	}

}
