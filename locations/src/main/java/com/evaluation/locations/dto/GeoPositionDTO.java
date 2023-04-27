package com.evaluation.locations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GeoPositionDTO {
    @JsonProperty("Latitude")
    private float latitude;
    @JsonProperty("Longitude")
    private float longitude;
    @JsonProperty("Elevation")
    private ElevationDTO elevation;
}
