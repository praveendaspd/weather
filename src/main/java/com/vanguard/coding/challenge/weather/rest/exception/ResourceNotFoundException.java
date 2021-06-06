/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author praveendas
 *
 */
/**
 * Thrown when the server did not find a current representation for the target resource or is not
 * willing to disclose that one exists
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "Resource Not Found!")
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6599470437664657718L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
