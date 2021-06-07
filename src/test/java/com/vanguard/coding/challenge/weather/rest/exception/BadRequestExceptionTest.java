/**
 * 
 */
package com.vanguard.coding.challenge.weather.rest.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import com.vanguard.coding.challenge.weather.rest.service.WeatherService;

/**
 * @author praveendas
 *
 */
@WebMvcTest
@ComponentScan(basePackages = "com.vanguard")
public class BadRequestExceptionTest {
	
	private static final String INVALID_URL = "/getWeatherDetails?country=AU&apiKey=c8aadb8f4504f95b5a9144313cd96f84";
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;
	
	@Test
	public void validateException() throws Exception {
		
		Assertions.assertThrows(Exception.class, () -> {
			mockMvc.perform(get(null));
		});
		
	}

}
