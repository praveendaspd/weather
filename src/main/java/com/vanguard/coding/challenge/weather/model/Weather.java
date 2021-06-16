/**
 * 
 */
package com.vanguard.coding.challenge.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author praveendas
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Weather {

	private int id;
	private String main;
	private String description;
	private String icon;

	@Override
	public String toString() {
		return "Weather{ id= " + id + ", main= '" + main + "' , "
				+ "description= '" + description + "', icon= '" + icon	+ "' }";
	}

}
