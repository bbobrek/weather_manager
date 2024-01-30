package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.current;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetails;

@Value
public class CurrentConditionsResponse {

    @JsonProperty("cloud_coverage")
    Integer cloudCoverage;

    @JsonProperty("feels_like")
    Integer feelsLike;
    Integer humidity;

    @JsonProperty("precipitation_probability")
    Integer precipitationProbability;
    Integer pressure;
    Integer rain;
    Integer snow;
    Integer temperature;
    long timestamp;

    WeatherDetails weather;
    WindDetails wind;

}