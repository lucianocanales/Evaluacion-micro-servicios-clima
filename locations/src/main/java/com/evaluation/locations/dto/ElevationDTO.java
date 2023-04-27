package com.evaluation.locations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ElevationDTO {
    @JsonProperty("Metric")
    private MetricDTO metric;
    @JsonProperty("Imperial")
    private ImperialDTO imperial;
}
