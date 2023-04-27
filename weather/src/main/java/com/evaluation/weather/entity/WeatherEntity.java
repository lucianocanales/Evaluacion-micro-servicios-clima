package com.evaluation.weather.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_weather")
public class WeatherEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localizedName;
    private String weatherType;
    private Date createDate;
    private Date weatherDate;
    private String dayDescriptions;
    private String nightDescriptions;
    @Column(nullable = true)
    private boolean dayHasPrecipitation;
    @Column(nullable = true)
    private boolean nightHasPrecipitation;
    private float maxTemperature;
    private float minTemperature;
}
