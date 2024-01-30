package pl.bbobrek.weather.manager.models.weather.response.visual;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class VisualDailyConditions implements PrecipitationConditions {
    private Instant datetimeEpoch;
    @JsonProperty("cloudcover")
    private Double cloudCoverage;
    @JsonProperty("feelslike")
    private Double feelsLike;
    private Double humidity;
    private Double pressure;
    private Double temp;
    @JsonProperty("tempmax")
    private Double tempMax;
    @JsonProperty("tempmin")
    private Double tempMin;
    @JsonProperty("windgust")
    private Double windGust;
    @JsonProperty("windspeed")
    private Double windSpeed;
    @JsonProperty("winddir")
    private Double windDirection;
    private String conditions;
    private String description;
    private Double precip;
    private String icon;
    private List<String> preciptype;
    private List<VisualHourlyConditions> hours;

}
