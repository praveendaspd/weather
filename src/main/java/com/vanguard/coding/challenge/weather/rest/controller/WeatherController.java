/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanguard.coding.challenge.weather.rest.domain.Weather;
import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

/**
 * @author praveendas
 *
 */
@RestController
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/getWeather")
	public ResponseEntity<Weather> getCurrentWeather(){
		
		Weather weather = new Weather();
		
		weather = weatherService.getWeatherDetails();
		
		return new ResponseEntity<Weather>(weather,HttpStatus.OK);
	}

}
