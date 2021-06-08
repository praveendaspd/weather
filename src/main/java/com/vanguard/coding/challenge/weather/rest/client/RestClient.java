/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vanguard.coding.challenge.weather.common.AppConstants;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

/**
 * Spring managed Component to make REST API calls to OpenWeatherMap
 * 
 * @author praveendas
 *
 */
@Component
public class RestClient {

	RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

	final static String uri = "http://api.openweathermap.org/data/2.5/weather?q=Melbourne&appid=c8aadb8f4504f95b5a9144313cd96f81";

	/**
	 * Method to call the OpenWeatherMap API
	 * @param weatherEntity 
	 * 
	 * @return {@link WeatherDetails}
	 */
	public WeatherDetails makeRestCall(WeatherEntity weatherEntity) {

		WeatherDetails weatherDetails = new WeatherDetails();
		
		String uri = constructUri(weatherEntity);

		// Call the OpenWeatherMap API
		weatherDetails = restTemplate.getForObject(uri, WeatherDetails.class);

		return weatherDetails;
	}

	/**
	 * Build the final URI using the weather entity
	 * 
	 * @param weatherEntity
	 * 
	 * @return String URI
	 */
	private String constructUri(WeatherEntity weatherEntity) {
		
		StringBuffer URI = new StringBuffer(AppConstants.OPEN_WEATHER_MAP_QUERY);
		
		URI.append(weatherEntity.getCity());
		URI.append(",");
		URI.append(weatherEntity.getCountry());
		URI.append("&appid=");
		URI.append(AppConstants.API_KEY_2);
		
		logger.info(URI.toString());
		
		return URI.toString();
		
	}

}
