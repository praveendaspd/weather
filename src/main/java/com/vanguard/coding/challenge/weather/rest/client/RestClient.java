/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;

/**
 * Spring managed Component to make REST API calls to OpenWeatherMap
 * 
 * @author praveendas
 *
 */
@Component
public class RestClient {

	RestTemplate restTemplate = new RestTemplate();

	Logger logger = LoggerFactory.getLogger(RestClient.class);

	final static String uri = "http://api.openweathermap.org/data/2.5/weather?q=Melbourne&appid=c8aadb8f4504f95b5a9144313cd96f81";

	/**
	 * Method to call the OpenWeatherMap API
	 * 
	 * @return {@link WeatherDetails}
	 */
	public WeatherDetails makeRestCall() {

		WeatherDetails weatherDetails = new WeatherDetails();

		// Call the OpenWeatherMap API
		weatherDetails = restTemplate.getForObject(uri, WeatherDetails.class);

		return weatherDetails;
	}

}
