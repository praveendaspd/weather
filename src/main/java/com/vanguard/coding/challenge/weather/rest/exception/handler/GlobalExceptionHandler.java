/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception.handler;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.vanguard.coding.challenge.weather.common.AppConstants;
import com.vanguard.coding.challenge.weather.rest.exception.BadRequestException;
import com.vanguard.coding.challenge.weather.rest.exception.InternalServerException;
import com.vanguard.coding.challenge.weather.rest.exception.UnauthorizedAccessException;

import lombok.extern.slf4j.Slf4j;

/**
 * This handler is used as a common place for Error handling 
 * as well as similar Error Response body with a proper HTTP status code across APIs
 * 
 * @author praveendas
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	/**
	 * Handle HttpClientErrorException
	 * 
	 * @param HttpClientErrorException
	 * 
	 * @return ResponseEntity with UnauthorizedAccessException
	 */
	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientException(HttpClientErrorException e) {

		UnauthorizedAccessException exception = new UnauthorizedAccessException(e.getLocalizedMessage());
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
		
	}
	
	
	/**
	 * Handle ConstraintViolationException and throw a meaningful message back as part of the ResponseEntity
	 * 
	 * @param ConstraintViolationException
	 * 
	 * @return ResponseEntity with HTTPStatus and message
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> handleBadRequestException(ConstraintViolationException e) {

		BadRequestException exception = new BadRequestException(e.getLocalizedMessage());
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(value = InternalServerError.class)
	public ResponseEntity<String> handleInternalServerError(InternalServerError e) {

		InternalServerException exception = new InternalServerException(e.getLocalizedMessage());
		logger.info(exception.getMessage());
		
		return new ResponseEntity<String>(AppConstants.INTERNAL_SERVER_EXCEPTION_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
