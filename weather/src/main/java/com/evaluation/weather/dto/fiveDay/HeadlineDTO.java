package com.evaluation.weather.dto.fiveDay;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HeadlineDTO {

    @JsonProperty("EffectiveDate")
    private Date effectiveDate;
    @JsonProperty("EffectiveEpochDate")
    private int effectiveEpochDate;
    @JsonProperty("Severity")
    private int severity;
    @JsonProperty("Text")
    private String text;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("EndDate")
    private Date endDate;
    @JsonProperty("EndEpochDate")
    private int endEpochDate;
    @JsonProperty("MobileLink")
    private String mobileLink;
    @JsonProperty("Link")
    private String link;
}
