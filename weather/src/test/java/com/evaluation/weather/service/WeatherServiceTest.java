package com.evaluation.weather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.client.RestTemplate;

import com.evaluation.weather.entity.WeatherEntity;
import com.evaluation.weather.repository.WeatherRepository;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private WeatherRepository  weatherRepository;

    @InjectMocks
    private WeatherService weatherService;
    
 
    @Test
    void testGetAll() {
        List<WeatherEntity> weatherEntities = weatherService.getAll();
        assertNotNull(weatherEntities);
    }

    
}
