package com.evaluation.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.weather.entity.WeatherEntity;
import com.evaluation.weather.service.WeatherService;

@RestController
@RequestMapping("/api/v1/Weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatheService;


    /**
     * Primero revisa si los datos de 5 dias de pronostico estan en la 
     * base de datos si no los encuentra los busca en la API de accuweather
     * 
     * @param name
     * @return
     */
    @GetMapping("/5day/{name}")
    public ResponseEntity<List<WeatherEntity>> getWeatherFiveDay(@PathVariable("name") String name){
       
        List<WeatherEntity> weatherEntities = weatheService.getFiveDay(name);
        if (weatherEntities.isEmpty()) {
            List<WeatherEntity> externalWeatherEntities = weatheService.getExternalWeatherbyFiveDay(name);
            if (externalWeatherEntities.isEmpty()) {
                return ResponseEntity.notFound().build(); 
            }
            return ResponseEntity.ok(externalWeatherEntities);
        }
        return ResponseEntity.ok(weatherEntities);
    }

    
    /**
     * Primero revisa si los datos de las ultimas 12 hs de pronostico estan en la 
     * base de datos si no los encuentra los busca en la API de accuweather
     * 
     * @param name
     * @return
     */
    @GetMapping("/12hour/{name}")
    public ResponseEntity<List<WeatherEntity>> getWeatherTwelveHour(@PathVariable("name") String name){
       
        List<WeatherEntity> weatherEntities = weatheService.getTwelveHour(name);
        if (weatherEntities.isEmpty()) {
            List<WeatherEntity> externalWeatherEntities = weatheService.getExternalWeatherbyTwelveHour(name);
            if (externalWeatherEntities.isEmpty()) {
                return ResponseEntity.notFound().build(); 
            }
            return ResponseEntity.ok(externalWeatherEntities);
        }
        return ResponseEntity.ok(weatherEntities);
    }

}
