/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguard.coding.challenge.weather.rest.client.RestClient;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.repository.WeatherRepository;

/**
 * Service Layer class for the Weather App
 * 
 * @author praveendas
 *
 */
@Service
public class WeatherService {

	@Autowired
	RestClient restClient;
	
	@Autowired
	WeatherRepository repository;

	public WeatherService(RestClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * Method to call the RestClient and map the ServieResponse to WeatherWrapper Entity 
	 * 
	 * @return {@link WeatherWrapper}
	 */
	public WeatherWrapper getWeatherDetails(WeatherEntity weatherEntity) {

		Logger logger = LoggerFactory.getLogger(WeatherService.class);
		
		WeatherDetails weatherDetails = new WeatherDetails();
		
		// Wrapper to only extract and store Description field
		WeatherWrapper wrapper = new WeatherWrapper();
		
		Optional<WeatherEntity> weather = repository.findByApiKey(weatherEntity.getApiKey());

		if(weather.isPresent()) {
			
			WeatherEntity entity = weather.get();
			logger.info("Data if Present- {} ",entity.toString());
			
			// Wrapper to only extract and store Description field
			wrapper.setDescription(entity.getDescription());
		}
		else {
			//RestClient to make API call to OpenWeatherMap
			weatherDetails = restClient.makeRestCall(weatherEntity);
			
			WeatherEntity entity = new WeatherEntity();
			entity.setCity(weatherEntity.getCity());
			entity.setCountry(weatherEntity.getCountry());
			entity.setApiKey(weatherEntity.getApiKey());
			entity.setDescription(weatherDetails.getWeather().get(0).getDescription());
			
			WeatherEntity ent = repository.saveAndFlush(entity);
			logger.info("Data after calling REST API and persting in DB - {} ",ent);
			
			wrapper.setDescription(weatherDetails.getWeather().get(0).getDescription());
			
			logger.info(weatherDetails.toString());
		}

		return wrapper;

	}

}
