package com.evaluation.weather.dto.fiveDay;

import lombok.Data;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DailyForecastsDTO {

    @JsonProperty("Date")
    private Date date;
    @JsonProperty("EpochDate")
    private long epochDate;
    @JsonProperty("Temperature")
    private TemperatureDTO temperature;
    @JsonProperty("Day")
    private DayDTO day;
    @JsonProperty("Night")
    private NightDTO night;
    @JsonProperty("Sources")
    private String[] sources;
    @JsonProperty("MobileLink")
    private String mobileLink;
    @JsonProperty("Link")
    private String link;
}
