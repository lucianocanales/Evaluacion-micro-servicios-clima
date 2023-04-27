package com.evaluation.weather.dto.fiveDay;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TemperatureDTO {
    @JsonProperty("Minimum")
    private MinimumDTO minimum;
    @JsonProperty("Maximum")
    private MaximumDTo maximum;

}
