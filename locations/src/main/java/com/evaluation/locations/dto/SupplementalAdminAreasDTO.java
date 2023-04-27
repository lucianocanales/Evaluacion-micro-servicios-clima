package com.evaluation.locations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SupplementalAdminAreasDTO {
    @JsonProperty("Level")
    private int level;
    @JsonProperty("LocalizedName")
    private String localizedName;
    @JsonProperty("EnglishName")
    private String englishName;
}
