package com.evaluation.weather.dto.fiveDay;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NightDTO {
    @JsonProperty("Icon")
    private int icon;
    @JsonProperty("IconPhrase")
    private String iconPhrase;
    @JsonProperty("HasPrecipitation")
    private boolean hasPrecipitation;
}
