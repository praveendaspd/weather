/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author praveendas
 *
 * Thrown when the server encountered an unexpected condition that prevented it from fulfilling the
 * request
 */
@ResponseStatus(value =HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error!")
public class InternalServerException extends BaseException {

	private static final long serialVersionUID = 1L;


	public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }

}

