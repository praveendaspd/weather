/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author praveendas
 * 
 * Thrown when the server did not find a current representation for the target resource or is not
 * willing to disclose that one exists
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found!")
public class ObjectNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;


	public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }


    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

}

