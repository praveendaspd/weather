/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanguard.coding.challenge.weather.model.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.exception.BadRequestException;
import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

/**
 * RestController class to return Response Object/Entity as a JSON Object Acts
 * as a Controller as well as ResponseBody
 * 
 * @author praveendas
 *
 */
@Slf4j
@Validated
@RestController
public class WeatherController {

	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/weather-forecast/region")
	public ResponseEntity<WeatherWrapper> getCurrentWeather(@RequestParam(name = "city", required = false) @NotNull String city,
			@RequestParam(name = "country", required = false) @NotNull String country,
			@RequestParam(name = "apiKey", required = false) String apiKey) throws BadRequestException {

		
		log.debug("city - {}", city);
		log.debug("country - {}", country);
		log.debug("apiKey - {}", apiKey);

		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCity(city);
		weatherEntity.setCountry(country);
		weatherEntity.setApiKey(apiKey);

		WeatherWrapper wrapper = new WeatherWrapper();

		// Service layer call
		wrapper = weatherService.getWeatherDetails(weatherEntity);

		log.info(wrapper.toString());

		return new ResponseEntity<WeatherWrapper>(wrapper, HttpStatus.OK);
	}

}
