package com.evaluation.weather.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.evaluation.weather.repository.WeatherRepository;

@DataJpaTest
public class WeatherEntityTests {
    
    @Autowired
    private WeatherRepository weatherRepository;


    @Test
    public void saveWeatetherTests() {
        WeatherEntity weatherEntity = new WeatherEntity(
            null, 
            "Buenos Aires", 
            "D", 
            new Date(), 
            new Date(), 
            "nobosidad variable", 
            "", 
            true,
            false,
            18, 
            25
          );
        WeatherEntity savedWeatherEntity = weatherRepository.save(weatherEntity);
        assertNotNull(savedWeatherEntity);
    }

}
