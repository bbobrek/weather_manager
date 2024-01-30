package pl.bbobrek.weather.manager.domain.weather.result.details;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeatherIcon {

    SNOW("snow", 601),
    SNOW_SHOWERS_DAY("snow-showers-day", 621),
    SNOW_SHOWERS_NIGHT("snow-showers-night", 621),
    THUNDER_RAIN("thunder-rain", 201),
    THUNDER_SHOWERS_DAY("thunder-showers-day", 202),
    THUNDER_SHOWERS_NIGHT("thunder-showers-night", 202),
    RAIN("rain", 501),
    SHOWERS_DAY("showers-day", 502),
    SHOWERS_NIGHT("showers-night", 502),
    FOG("fog", 741),
    WIND("wind", 771),
    CLOUDY("cloudy", 804),
    PARTLY_CLOUDY_DAY("partly-cloudy-day", 802),
    PARTLY_CLOUDY_NIGHT("partly-cloudy-night", 802),
    CLEAR_SKY("clear-day", 800);

    private final String providerIconName;
    private final int id;

}
