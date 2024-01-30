package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.hourly;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetails;

@Value
public class HourlyConditionsResponse {

    @JsonProperty("cloud_coverage")
    Integer cloudCoverage;
    @JsonProperty("precipitation_probability")
    Integer precipitationProbability;
    @JsonProperty("feels_like")
    Integer feelsLike;
    Integer humidity;
    Integer pressure;
    Integer rain;
    Integer snow;
    Integer temperature;
    long timestamp;
    WeatherDetails weather;
    WindDetails wind;

}
