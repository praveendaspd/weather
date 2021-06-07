/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author praveendas
 *
 * Thrown when request has not been applied because it lacks valid
 * authentication credentials for the target resource
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
public class UnauthorizedAccessException extends BaseException {

	private static final long serialVersionUID = 1L;


	public UnauthorizedAccessException() {
        super();
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

}