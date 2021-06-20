package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Oferta;

@Component
public class AddOfertaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Oferta.class.equals(clazz);
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object target, Errors errors) {
		Oferta oferta = (Oferta) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		
	}

}
