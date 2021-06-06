/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.vanguard.coding.challenge.weather.rest.exception.BadRequestException;
import com.vanguard.coding.challenge.weather.rest.exception.UnauthorizedAccessException;

/**
 * This handler is used as a common place for Error handling 
 * as well as similar Error Response body with a proper HTTP status code across APIs
 * 
 * @author praveendas
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handle the Bind Exceptions when the mandatory request parameters are not provided
	 * 
	 * @param @MissingServletRequestParameterException
	 * 
	 * @return custom exception - @BadRequestException
	 * 
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleBindException(MissingServletRequestParameterException e) {

		BadRequestException exception = new BadRequestException(e.getLocalizedMessage());
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientException(HttpClientErrorException e) {

		UnauthorizedAccessException exception = new UnauthorizedAccessException(e.getLocalizedMessage());
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}
}
