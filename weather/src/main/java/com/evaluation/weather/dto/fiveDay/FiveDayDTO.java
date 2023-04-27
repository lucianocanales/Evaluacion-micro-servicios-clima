package com.evaluation.weather.dto.fiveDay;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FiveDayDTO {
    @JsonProperty("Headline")
    private HeadlineDTO headline;
    @JsonProperty("DailyForecasts")
    private List<DailyForecastsDTO> dailyForecasts;
}
