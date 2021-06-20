package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Sale;


@Component
public class SaleValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Sale.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sale sale = (Sale) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "detalle", "Error.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "Error.vacio");
		
		if (sale.getPrecio() <= 0.0) {
			errors.rejectValue("precio", "Error.precio");
		}
		

	}
}
