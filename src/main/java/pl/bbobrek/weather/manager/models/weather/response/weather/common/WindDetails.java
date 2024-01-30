package pl.bbobrek.weather.manager.models.weather.response.weather.common;

import lombok.Value;

@Value
public class WindDetails {
    Integer degrees;
    Integer gust;
    Integer speed;
}
