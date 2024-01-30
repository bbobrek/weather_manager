package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.daily;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetails;

@Value
public class DailyConditionsResponse {

    @JsonProperty("cloud_coverage")
    Integer cloudCoverage;
    Integer humidity;
    Integer pressure;
    Integer rain;
    Integer snow;
    long timestamp;
    WeatherDetails weather;
    WindDetails wind;
    @JsonProperty("feels_like")
    TemperatureDetails feelsLike;
    TemperatureDetails temperature;

    @Value
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemperatureDetails {
        Integer day;
        Integer min;
        Integer max;
    }

}
