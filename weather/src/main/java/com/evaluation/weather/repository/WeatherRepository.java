package com.evaluation.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.weather.entity.WeatherEntity;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer>{
    List<WeatherEntity> findByLocalizedName(String localizedName);
    List<WeatherEntity> findByWeatherTypeAndLocalizedName(String weatherType,String localizedName);
}
