/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.exception.BadRequestException;
import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

/**
 * RestController class to return Response Object/Entity as a JSON Object
 * Acts as a Controller as well as ResponseBody
 * 
 * @author praveendas
 *
 */
@Slf4j
@RestController
public class WeatherController {

	Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@Autowired
	WeatherService weatherService;

	@GetMapping("/getWeatherDetails")
	public ResponseEntity<WeatherWrapper> getCurrentWeather(@RequestParam(name="city" , required = false) String city,
															@RequestParam(name="country", required = false) String country,
															@RequestParam(name="apiKey" , required = false) String apiKey) throws BadRequestException{
		
		//Validation to throw the field specific error back to the caller
		if(city == null ) {
			throw new BadRequestException("city field is required!");
		}
		
		//Validation to throw the field specific error back to the caller
		if(country == null) {
			throw new BadRequestException("country field is required!");
		}
		
		
		logger.debug("city - {}",city);
		logger.debug("country - {}",country);
		logger.debug("apiKey - {}",apiKey);
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCity(city);
		weatherEntity.setCountry(country);
		weatherEntity.setApiKey(apiKey);

		WeatherWrapper wrapper = new WeatherWrapper();

		// Service layer call
		wrapper = weatherService.getWeatherDetails(weatherEntity);

		logger.info(wrapper.toString());

		return new ResponseEntity<WeatherWrapper>(wrapper, HttpStatus.OK);
	}

}
