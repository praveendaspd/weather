package com.vanguard.coding.challenge.weather.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long>{
	
	Optional<WeatherEntity> findByApiKey(String apiKey);
	
	Optional<WeatherEntity> findByCityAndCountry(String city, String country);

}
