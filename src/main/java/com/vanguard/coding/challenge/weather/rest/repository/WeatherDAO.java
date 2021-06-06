/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.repository;

import java.util.Optional;

import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

/**
 * @author praveendas
 *
 */
public interface WeatherDAO {
	
	Optional<WeatherEntity> get(long id);

}
