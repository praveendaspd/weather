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
import org.springframework.web.bind.annotation.RestController;

import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

/**
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
	public ResponseEntity<WeatherWrapper> getCurrentWeather() {

		WeatherWrapper wrapper = new WeatherWrapper();

		// Service layer call
		wrapper = weatherService.getWeatherDetails();

		logger.info(wrapper.toString());

		return new ResponseEntity<WeatherWrapper>(wrapper, HttpStatus.OK);
	}

}
