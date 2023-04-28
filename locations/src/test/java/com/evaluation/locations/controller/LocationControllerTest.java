package com.evaluation.locations.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.evaluation.locations.entity.LocationEntity;
import com.evaluation.locations.service.LocationService;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private LocationService locationService;

    private List<LocationEntity> locationEntities ;

    @BeforeEach
    void setUp(){
        locationEntities = new ArrayList<LocationEntity>();

        LocationEntity locationEntity = new LocationEntity(
            (long)1,
            "7493", 
            "Pinamar"
          );
        locationEntities.add(locationEntity);
    }

    @Test
    void testGetAll() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/location").contentType(MediaType.APPLICATION_JSON);
        when(locationService.getAll()).thenReturn(locationEntities);
        MvcResult result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
            
        String expected = "[{id: 1, key: \"7493\", localizedName: \"Pinamar\"}]";
        String resultString = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expected, resultString, false);

        List<LocationEntity> locationEntities = locationService.getAll();
        assertNotNull(locationEntities);
    }

    @Test
    void testGetLocationByName() throws Exception {
        RequestBuilder requestBuilder = get("/api/v1/location/Pinamar").contentType(MediaType.APPLICATION_JSON);
        when(locationService.getLocationByName("Pinamar")).thenReturn(locationEntities);
        MvcResult result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
            
        String expected = "{id: 1, key: \"7493\", localizedName: \"Pinamar\"}";
        String resultString = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expected, resultString, false);

        List<LocationEntity> locationEntities = locationService.getAll();
        assertNotNull(locationEntities);
    }
}
