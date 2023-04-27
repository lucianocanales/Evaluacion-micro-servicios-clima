package com.evaluation.weather.dto.fiveDay;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MinimumDTO {
    @JsonProperty("Value")
    private float value;
    @JsonProperty("Unit")
    private String unit;
    @JsonProperty("UnitType")
    private int unitType;
}
