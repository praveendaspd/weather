/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.vanguard.coding.challenge.weather.rest.domain.Weather;
import com.vanguard.coding.challenge.weather.rest.domain.WeatherDetails;
import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

/**
 * @author praveendas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {
	
	@Mock
	RestTemplate restTemplate = mock(RestTemplate.class);
	
	RestClient restClient = new RestClient();
	
	final static String uri = "http://localhost:8080/test";
	
	@Test
	public void testMakeRestCall() {
		
		WeatherEntity weatherEntity = new WeatherEntity();
		weatherEntity.setCountry("AU");
		weatherEntity.setCity("Melbourne");
		weatherEntity.setApiKey("123");
		
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
		
		when(restTemplate.getForObject(uri, WeatherDetails.class)).thenReturn(weatherDetails);
		
		WeatherDetails details = restClient.makeRestCall(weatherEntity);
		assertNotNull(details);
		
	}

}
