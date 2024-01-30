package pl.bbobrek.weather.manager.models.weather.response.visual;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Data
public class  VisualCurrentConditions implements PrecipitationConditions {
    private LocalTime datetime;
    private Instant datetimeEpoch;
    private Double temp;
    @JsonProperty("feelslike")
    private Double feelsLike;
    private Double humidity;
    private Double snow;
    private Double pressure;
    @JsonProperty("precipprob")
    private Double precipitationProbability;
    @JsonProperty("cloudcover")
    private Double cloudCoverage;
    @JsonProperty("sunriseEpoch")
    private Instant sunrise;
    @JsonProperty("sunsetEpoch")
    private Instant sunset;
    @JsonProperty("windgust")
    private Double windGust;
    @JsonProperty("windspeed")
    private Double windSpeed;
    @JsonProperty("winddir")
    private Double windDirection;
    private String icon;
    private List<String> preciptype;
    private Double precip;
    private String conditions;

}
