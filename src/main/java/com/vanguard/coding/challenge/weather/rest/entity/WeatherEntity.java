/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.entity;

import lombok.Data;

/**
 * @author praveendas
 *
 */
@Data
public class WeatherEntity {
	
	private Long id;
	
	private String city;
	
	private String country;
	
	private String apiKey;
	
	private String description;
	
}
