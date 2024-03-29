/**
 * 
 */
package com.vanguard.coding.challenge.weather.common;

/**
 * @author praveendas
 *
 */
public class AppConstants {
	
	//API Keys
	public static String API_KEY_1 = "c8aadb8f4504f95b5a9144313cd96f80";
	// The actual apiKey received from OpenWeather
	public static String API_KEY_2 = "c8aadb8f4504f95b5a9144313cd96f81";
	public static String API_KEY_3 = "c8aadb8f4504f95b5a9144313cd96f82";
	public static String API_KEY_4 = "c8aadb8f4504f95b5a9144313cd96f83";
	public static String API_KEY_5 = "c8aadb8f4504f95b5a9144313cd96f84";
	
	//Generic Messages
	public static String APP_START_UP_MSG = "###### WeatherApplication is up and running ##### ";
	
	//Error messages
	public static String API_THROTTLE_ERROR_MESSAGE = "Too many requests! Maximum requests allowed per hour is - ";
	public static String INTERNAL_SERVER_EXCEPTION_MESSAGE = "Oops, something bad happened!"; 
	
	//Validations
	public static String CITY_VALIDATION_MESSAGE = "city field is required!";
	public static String COUNTRY_VALIDATION_MESSAGE = "country field is required!";
	public static String API_KEY_VALIDATION_MESSAGE = "API Key Validation failed - Provide API KEY!";
	public static String API_KEY_INVALID_MESSAGE = "API Key Validation failed - Invalid API KEY : ";
	
}
