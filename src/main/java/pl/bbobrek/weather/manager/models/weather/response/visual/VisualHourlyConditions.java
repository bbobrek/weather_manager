package pl.bbobrek.weather.manager.models.weather.response.visual;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class VisualHourlyConditions implements PrecipitationConditions {
    private Instant datetimeEpoch;
    @JsonProperty("cloudcover")
    private Double cloudCoverage;
    @JsonProperty("feelslike")
    private Double feelsLike;
    @JsonProperty("precipprob")
    private Double precipitationProbability;
    private Double humidity;
    private Double pressure;
    private Double temp;
    @JsonProperty("windgust")
    private Double windGust;
    @JsonProperty("windspeed")
    private Double windSpeed;
    @JsonProperty("winddir")
    private Double windDirection;
    private Double precip;
    private List<String> preciptype;
    private String conditions;
    private String icon;

}
