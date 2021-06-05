/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author praveendas
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherDetails {

	private String name;
	private List<Weather> weather;

	@Override
	public String toString() {
		return "WeatherDetails{ name= '" + name + "', weather= " + weather + " }";
	}

}
