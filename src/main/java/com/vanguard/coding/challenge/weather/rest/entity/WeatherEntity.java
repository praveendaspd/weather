/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author praveendas
 *
 */
@Data
@Entity
@Table(name = "weather")
public class WeatherEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "description")
	private String description;

	public WeatherEntity() {

	}

	
	@Override
	public String toString() {
		return "WeatherEntity{ city= " + city + "', country= '" + country + "' , "
				+ "apiKey= '" + apiKey + "', description= '" + description	+ "' }";
	}

}
