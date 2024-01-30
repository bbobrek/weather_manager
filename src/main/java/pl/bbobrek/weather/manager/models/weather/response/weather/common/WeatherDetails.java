package pl.bbobrek.weather.manager.models.weather.response.weather.common;

import lombok.Value;

@Value
public class WeatherDetails {

    String description;
    String main;
    //icon id
    Integer id;

}
