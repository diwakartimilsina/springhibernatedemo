package com.tonearena.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tonearena.dto.ValidationErrorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
 
@ControllerAdvice
public class RestErrorHandler {
	
	    private static final Logger LOGGER = Logger.getLogger(RestErrorHandler.class);

	    private MessageSource messageSource;

	    @Autowired
	    public RestErrorHandler(MessageSource messageSource) {
	        this.messageSource = messageSource;
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
	        LOGGER.debug("Handling form validation error");

	        BindingResult result = ex.getBindingResult();
	        List<FieldError> fieldErrors = result.getFieldErrors();

	        return processFieldErrors(fieldErrors);
	    }

	    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
	        ValidationErrorDTO dto = new ValidationErrorDTO();

	        for (FieldError fieldError: fieldErrors) {
	            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
	            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
	        }

	        return dto;
	    }
	    
	    @ExceptionHandler(ConstraintViolationException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ValidationErrorDTO processValidationError(ConstraintViolationException ex) {
	        List<FieldError> violations = new ArrayList<FieldError>();
	        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	            violations.add(new FieldError(violation.getPropertyPath().toString(), violation.getPropertyPath().toString(), violation.getInvalidValue().toString()));
	        }
	        return processFieldErrors(violations);
	    }
	    
	    private String resolveLocalizedErrorMessage(FieldError fieldError) {
	        Locale currentLocale = LocaleContextHolder.getLocale();
	        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

	        //If a message was not found, return the most accurate field error code instead.
	        //You can remove this check if you prefer to get the default error message.
	        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
	            String[] fieldErrorCodes = fieldError.getCodes();
	            localizedErrorMessage = fieldErrorCodes[0];
	        }

	        return localizedErrorMessage;
	    }
}