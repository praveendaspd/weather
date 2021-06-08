package com.vanguard.coding.challenge.weather.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

@WebMvcTest
@ComponentScan(basePackages = "com.vanguard")
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;
	
	private static final String VALID_URL = "/weather-forecast/region?city=Melbourne&country=AU&apiKey=c8aadb8f4504f95b5a9144313cd96f84";
	private static final String INVALID_URL = "/weather-forecast/region?country=AU&apiKey=c8aadb8f4504f95b5a9144313cd96f84";
	private static final String INVALID_API_KEY = "/weather-forecast/region?city=Melbourne&country=AU&apiKey=c8aadb8f4504f95b5a9144313cd96f89";
	private static final String INVALID_URL_COUNTRY = "/weather-forecast/region?city=Melbourne&apiKey=c8aadb8f4504f95b5a9144313cd96f84";
	private static final String MISSING_API_KEY = "/weather-forecast/region?city=Melbourne&country=AU";


	@Test
	public void testGetWeatherService() throws Exception {

		WeatherWrapper weatherWrapper = new WeatherWrapper();
		weatherWrapper.setDescription("Overcast clouds");
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCountry("AU");
		weatherEntity.setCity("Melbourne");
		weatherEntity.setApiKey("c8aadb8f4504f95b5a9144313cd96f84");

		Mockito.when(weatherService.getWeatherDetails(weatherEntity)).thenReturn(weatherWrapper);

		mockMvc.perform(get(VALID_URL)).andExpect(status().isOk())
				.andExpect(jsonPath("description", Matchers.equalTo("Overcast clouds")));

	}

	@Test
	public void testGetWeatherService_negative_isNotFound() throws Exception {

		WeatherWrapper weatherWrapper = new WeatherWrapper();
		weatherWrapper.setDescription("Overcast clouds");

		Mockito.when(weatherService.getWeatherDetails(new WeatherEntity())).thenReturn(weatherWrapper);
		mockMvc.perform(get(INVALID_URL)).andExpect(status().isBadRequest());

	}
	
	@Test
	public void testGetWeatherService_negative_isUnauthorized() throws Exception {

		WeatherWrapper weatherWrapper = new WeatherWrapper();
		weatherWrapper.setDescription("Overcast clouds");

		Mockito.when(weatherService.getWeatherDetails(new WeatherEntity())).thenReturn(weatherWrapper);
		mockMvc.perform(get(INVALID_API_KEY)).andExpect(status().isUnauthorized());

	}
	
	@Test
	public void testGetWeatherService_negative_noApiKey() throws Exception {

		WeatherWrapper weatherWrapper = new WeatherWrapper();
		weatherWrapper.setDescription("Overcast clouds");

		Mockito.when(weatherService.getWeatherDetails(new WeatherEntity())).thenReturn(weatherWrapper);
		mockMvc.perform(get(MISSING_API_KEY)).andExpect(status().isBadRequest());

	}
	
	@Test
	public void testGetWeatherService_negative_validateCounty() throws Exception {

		WeatherWrapper weatherWrapper = new WeatherWrapper();
		weatherWrapper.setDescription("Overcast clouds");

		Mockito.when(weatherService.getWeatherDetails(new WeatherEntity())).thenReturn(weatherWrapper);
		mockMvc.perform(get(INVALID_URL_COUNTRY)).andExpect(status().isBadRequest());

	}
	
}
