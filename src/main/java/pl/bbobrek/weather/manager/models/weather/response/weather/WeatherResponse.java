package pl.bbobrek.weather.manager.models.weather.response.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Value;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.daily.DailyConditionsResponse;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.hourly.HourlyConditionsResponse;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.current.CurrentConditionsResponse;

import java.util.List;

@Value
@JsonTypeName(value = "data")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class WeatherResponse {

    @JsonProperty("city_name")
    String city;
    long sunrise;
    long sunset;
    CurrentConditionsResponse current;
    List<DailyConditionsResponse> daily;
    List<HourlyConditionsResponse> hourly;

}
