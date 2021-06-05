/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguard.coding.challenge.weather.rest.client.RestClient;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;

/**
 * Service Layer class for the Weather App
 * 
 * @author praveendas
 *
 */
@Service
public class WeatherService {

	@Autowired
	private RestClient restClient;

	public WeatherService(RestClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * Method to call the RestClient and map the ServieResponse to WeatherWrapper Entity 
	 * 
	 * @return {@link WeatherWrapper}
	 */
	public WeatherWrapper getWeatherDetails() {

		Logger logger = LoggerFactory.getLogger(WeatherService.class);

		//RestClient to make API call to OpenWeatherMap
		WeatherDetails weatherDetails = restClient.makeRestCall();
		
		logger.info(weatherDetails.toString());
		
		// Wrapper to only extract and store Description field
		WeatherWrapper wrapper = new WeatherWrapper();
		wrapper.setDescription(weatherDetails.getWeather().get(0).getDescription());

		return wrapper;

	}

}
