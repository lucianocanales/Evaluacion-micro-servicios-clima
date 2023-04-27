package com.evaluation.weather.dto.twelveHour;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TwelveHourDTO {
    @JsonProperty("DateTime")
    private Date dateTime;
    @JsonProperty("EpochDateTime")
    private long epochDateTime;
    @JsonProperty("WeatherIcon")
    private int weatherIcon;
    @JsonProperty("IconPhrase")
    private String iconPhrase;
    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;
    @JsonProperty("IsDaylight")
    private boolean isDaylight;
    @JsonProperty("Temperature")
    private TemperatureDTO temperature;
    @JsonProperty("PrecipitationProbability")
    private float precipitationProbability;
    @JsonProperty("MobileLink")
    private String mobileLink;
    @JsonProperty("Link")
    private String link;
}
