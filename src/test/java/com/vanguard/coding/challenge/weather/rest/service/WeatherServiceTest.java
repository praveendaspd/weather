/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.vanguard.coding.challenge.weather.rest.client.RestClient;
import com.vanguard.coding.challenge.weather.rest.domain.Weather;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherWrapper;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;
import com.vanguard.coding.challenge.weather.rest.repository.WeatherRepository;

/**
 * @author praveendas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {
	
	RestClient restClient = mock(RestClient.class);
	
	WeatherRepository repository = mock(WeatherRepository.class);	
	
	WeatherService weatherService = new WeatherService(restClient,repository);
	
	@Test
	public void testWeatherService_restClient() {
		
		WeatherDetails weatherDetails = new WeatherDetails();
		List<Weather> weathers = new ArrayList<Weather>();
		Weather weather = new Weather();
		weather.setId(1234);
		weather.setMain("Cloudy");
		weather.setIcon("10n");
		weather.setDescription("Partly Cloudy");
		weathers.add(weather);
		
		weatherDetails.setName("Melbourne");
		weatherDetails.setWeather(weathers);
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCountry("AU");
		weatherEntity.setCity("Melbourne");
		weatherEntity.setApiKey("123");
		
		when(restClient.makeRestCall(weatherEntity)).thenReturn(weatherDetails);
		
		WeatherWrapper wrapper = weatherService.getWeatherDetails(weatherEntity);
		
		assertEquals(wrapper.getDescription(), "Partly Cloudy"); 
		
	}
	
	@Test
	public void testWeatherService_fetchFrom_repository() {
		
		WeatherDetails weatherDetails = new WeatherDetails();
		List<Weather> weathers = new ArrayList<Weather>();
		Weather weather = new Weather();
		weather.setId(1234);
		weather.setMain("Cloudy");
		weather.setIcon("10n");
		weather.setDescription("Partly Cloudy");
		weathers.add(weather);
		
		weatherDetails.setName("Melbourne");
		weatherDetails.setWeather(weathers);
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCountry("AU");
		weatherEntity.setCity("Melbourne");
		weatherEntity.setApiKey("123");
		weatherEntity.setDescription("Scattered Clouds");
		
		when(repository.findByCityAndCountry("Melbourne", "AU")).thenReturn(Optional.ofNullable(weatherEntity));
		
		WeatherWrapper wrapper = weatherService.getWeatherDetails(weatherEntity);
		
		assertEquals(wrapper.getDescription(), "Scattered Clouds"); 
		
	}
	
	@Test
	public void testWeatherService_negative() {
		
		WeatherDetails weatherDetails = new WeatherDetails();
		List<Weather> weathers = new ArrayList<Weather>();
		Weather weather = new Weather();
		weather.setId(1234);
		weather.setMain("Cloudy");
		weather.setIcon("10n");
		weather.setDescription("No Clouds");
		weathers.add(weather);
		
		weatherDetails.setName("Melbourne");
		weatherDetails.setWeather(weathers);
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCountry("AU");
		weatherEntity.setCity("Melbourne");
		weatherEntity.setApiKey("123");
		
		when(restClient.makeRestCall(weatherEntity)).thenReturn(weatherDetails);
		
		WeatherWrapper wrapper = weatherService.getWeatherDetails(weatherEntity);
		
		assertNotEquals(wrapper.getDescription(), "Partly Cloudy"); 
		
	}

}
