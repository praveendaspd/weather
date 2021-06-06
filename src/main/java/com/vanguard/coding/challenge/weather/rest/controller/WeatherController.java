/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
	public ResponseEntity<WeatherWrapper> getCurrentWeather(@RequestParam(name="city") String city,
															@RequestParam(name="country") String country,
															@RequestParam(name="apiKey") String apiKey) throws BadRequestException{
		
		logger.info("city - {}",city);
		logger.info("country - {}",country);
		logger.info("apiKey - {}",apiKey);
		
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
