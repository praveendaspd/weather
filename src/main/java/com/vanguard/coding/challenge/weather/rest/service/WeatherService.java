/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguard.coding.challenge.weather.model.WeatherDetails;
import com.vanguard.coding.challenge.weather.model.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.client.RestClient;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.repository.WeatherRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service Layer class for the Weather App
 * 
 * @author praveendas
 *
 */
@Slf4j
@Service
public class WeatherService {
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	WeatherRepository repository;

	public WeatherService(RestClient restClient, WeatherRepository repository) {
		this.restClient = restClient;
		this.repository = repository;
	}

	/**
	 * Method to call the RestClient and map the ServieResponse to WeatherWrapper Entity 
	 * 
	 * @return {@link WeatherWrapper}
	 */
	public WeatherWrapper getWeatherDetails(WeatherEntity weatherEntity) {

		WeatherDetails weatherDetails = new WeatherDetails();
		
		// Wrapper to only extract and store Description field
		WeatherWrapper wrapper = new WeatherWrapper();
		
		log.info("Weather Entity - {} ",weatherEntity.toString());
		
		//Always check the database before making call to the actual API 
		Optional<WeatherEntity> weather = repository.findByCityAndCountry(weatherEntity.getCity() , weatherEntity.getCountry());

		if(weather.isPresent()) {
			
			WeatherEntity entity = weather.get();
			log.info("Search data found in Database - {} ",entity.toString());
			
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
			
			WeatherEntity ent = repository.save(entity);
			log.info("Search Data unavailable. Calling OpenWeatherMap API and persting the response in DB - {} ",ent);
			
			wrapper.setDescription(weatherDetails.getWeather().get(0).getDescription());
			
			log.info(weatherDetails.toString());
		}

		return wrapper;

	}

}
