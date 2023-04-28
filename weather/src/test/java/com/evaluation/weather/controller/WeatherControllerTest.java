package com.evaluation.weather.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.evaluation.weather.entity.WeatherEntity;
import com.evaluation.weather.service.WeatherService;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private WeatherService weatherService;

    private List<WeatherEntity> weatherEntities;

    @BeforeEach
    void setUp(){

        weatherEntities = new ArrayList<WeatherEntity>();
        
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.APRIL, 28, 12, 30, 45);
        cal.set(Calendar.MILLISECOND, 123);
        long milisegundos = cal.getTimeInMillis();
        Date createDate = new Date(milisegundos);
        
        weatherEntities.add(
            new WeatherEntity(
                null, 
                "Pinamar", 
                "D", 
                createDate , 
                createDate, 
                "nobosidad variable", 
                "", 
                true,
                false,
                18f, 
                25f
            )
        );
    }
    
    @Test
    void testGetWeatherFiveDay() throws Exception{

        RequestBuilder requestBuilder = get("/api/v1/Weather/5day/pinamar").contentType(MediaType.APPLICATION_JSON);
        when(weatherService.getFiveDay("pinamar")).thenReturn(weatherEntities);
        MvcResult result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String expected = "[{id: null, localizedName: \"Pinamar\", weatherType: \"D\", createDate: \"2023-04-28T15:30:45.123+00:00\", "+
            "weatherDate:\"2023-04-28T15:30:45.123+00:00\", dayDescriptions: \"nobosidad variable\", nightDescriptions: \"\", "+
            "dayHasPrecipitation: true, nightHasPrecipitation: false, maxTemperature: 18.0,minTemperature: 25.0}]";
        String resultString = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expected, resultString, false);

    }

    @Test
    void testGetWeatherTwelveHour() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/Weather/12hour/Pinamar").contentType(MediaType.APPLICATION_JSON);
        when(weatherService.getTwelveHour("Pinamar")).thenReturn(weatherEntities);
        MvcResult result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        
        String expected = "[{id: null, localizedName: \"Pinamar\", weatherType: \"D\", createDate: \"2023-04-28T15:30:45.123+00:00\", "+
            "weatherDate:\"2023-04-28T15:30:45.123+00:00\", dayDescriptions: \"nobosidad variable\", nightDescriptions: \"\", "+
            "dayHasPrecipitation: true, nightHasPrecipitation: false, maxTemperature: 18.0,minTemperature: 25.0}]";
        String resultString = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expected, resultString, false);
    }
}
