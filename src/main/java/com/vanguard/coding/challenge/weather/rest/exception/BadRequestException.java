/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author praveendas
 *
 * Thrown when server cannot or will not process the request due to something that is perceived to
 * be a client error
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request!")
public class BadRequestException extends BaseException {

	private static final long serialVersionUID = 1L;


	public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

}

