/**
 * 
 */
package com.vanguard.coding.challenge.weather.model;

import lombok.Data;

/**
 * @author praveendas
 *
 */
@Data
public class WeatherWrapper {

	private String description;

	@Override
	public String toString() {

		return "WeatherWrapper{ description = '" + description + "' }";
	}

}
