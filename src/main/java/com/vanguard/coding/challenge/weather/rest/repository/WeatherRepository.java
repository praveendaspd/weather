package com.vanguard.coding.challenge.weather.rest.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vanguard.coding.challenge.weather.rest.entity.WeatherEntity;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherEntity, Long>{
	
	Optional<WeatherEntity> findByApiKey(String apiKey);
	
	Optional<WeatherEntity> findByCityAndCountry(String city, String country);

}
