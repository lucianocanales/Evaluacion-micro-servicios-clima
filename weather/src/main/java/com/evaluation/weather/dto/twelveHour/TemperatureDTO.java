package com.evaluation.weather.dto.twelveHour;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TemperatureDTO {
    @JsonProperty("Value")
    private float value;
    @JsonProperty("Unit")
    private String unit;
    @JsonProperty("UnitType")
    private int unitType;
}
