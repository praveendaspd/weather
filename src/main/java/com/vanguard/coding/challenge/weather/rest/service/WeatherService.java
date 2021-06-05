/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.service;

import org.springframework.stereotype.Service;

import com.vanguard.coding.challenge.weather.rest.domain.Weather;

/**
 * @author praveendas
 *
 */
@Service
public class WeatherService {

	public Weather getWeatherDetails() {

		Weather weather = new Weather();

		return weather;

	}

}
