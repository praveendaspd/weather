/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vanguard.coding.challenge.weather.model.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring managed Component to make REST API calls to OpenWeatherMap
 * 
 * @author praveendas
 *
 */
@Slf4j
@Component
public class RestClient {

	@Value("${api.uri}")
	private String uri;
	
	@Value("${api.key}")
	private String apiKey;
	
	RestTemplate restTemplate = new RestTemplate();


	/**
	 * Method to call the OpenWeatherMap API
	 * @param weatherEntity 
	 * 
	 * @return {@link WeatherDetails}
	 */
	public WeatherDetails makeRestCall(WeatherEntity weatherEntity) {

		WeatherDetails weatherDetails = new WeatherDetails();
		
		String url = constructUrl(weatherEntity);

		// Call the OpenWeatherMap API
		weatherDetails = restTemplate.getForObject(url, WeatherDetails.class);

		return weatherDetails;
	}

	/**
	 * Build the final URL using the weather entity
	 * 
	 * @param weatherEntity
	 * 
	 * @return String URL
	 */
	private String constructUrl(WeatherEntity weatherEntity) {
		
		StringBuffer URI = new StringBuffer(uri);
		
		URI.append(weatherEntity.getCity());
		URI.append(",");
		URI.append(weatherEntity.getCountry());
		URI.append("&appid=");
		URI.append(apiKey);
		
		log.info(URI.toString());
		
		return URI.toString();
		
	}

}
