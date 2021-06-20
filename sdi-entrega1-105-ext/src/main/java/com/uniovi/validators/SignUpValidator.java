package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;

@Component
public class SignUpValidator implements Validator {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	/**
	 * Validador del formulario de registro
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User user= (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "Error.vacio");
		
		
		//Email ya en uso
		if(userService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.signup.email.repetido");
		}
		
		//Las contraseñas no coinciden
		if(!user.getPassword2().equals(user.getPassword())) {
			errors.rejectValue("password2", "Error.signup.password2");
		}
		
	}

}
