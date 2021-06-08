package com.vanguard.coding.challenge.weather.rest.controller.validation;

import com.vanguard.coding.challenge.weather.common.AppConstants;
import com.vanguard.coding.challenge.weather.rest.exception.BadRequestException;

public class InputValidation {

	public boolean validateInputs(String country, String city) {

		if (city == null) {
			throw new BadRequestException(AppConstants.CITY_VALIDATION_MESSAGE);
		}

		if (country == null) {
			throw new BadRequestException(AppConstants.COUNTRY_VALIDATION_MESSAGE);
		}

		return true;

	}
}
