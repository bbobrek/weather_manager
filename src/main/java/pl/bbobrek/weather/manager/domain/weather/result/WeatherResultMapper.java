package pl.bbobrek.weather.manager.domain.weather.result;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocoding;
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetailsMapper;
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetailsMapper;
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetailsMapper;
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualWeatherResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring",
        uses = {CurrentWeatherDetailsMapper.class, DailyWeatherDetailsMapper.class, HourlyWeatherDetailsMapper.class, RandomUUIDMapper.class },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WeatherResultMapper {

    @Mapping(target = "creationDate", source = "visualWeatherResponse.currentConditions.datetime", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "sunrise", source = "visualWeatherResponse.currentConditions.sunrise")
    @Mapping(target = "sunset", source = "visualWeatherResponse.currentConditions.sunset")
    @Mapping(target = "city", source = "reverseGeocoding", qualifiedByName = "detectCityName")
    @Mapping(target = "currentWeatherDetails", source = "visualWeatherResponse.currentConditions")
    @Mapping(target = "dailyWeatherDetails", source = "visualWeatherResponse.days")
    @Mapping(target = "hourlyWeatherDetails", source = "visualWeatherResponse.hourlyConditions")
    @Mapping(target = "latitude", source = "visualWeatherResponse.latitude")
    @Mapping(target = "longitude", source = "visualWeatherResponse.longitude")
    @Mapping(target = "id", source = "visualWeatherResponse", qualifiedByName = {"randomUuIdMapper", "randomUuId"})
    WeatherResult toModel(VisualWeatherResponse visualWeatherResponse, ReverseGeocoding reverseGeocoding);

    @Named("toLocalDateTime")
    static LocalDateTime convertToLocalDateTime(LocalTime localTime) {
        if (localTime != null ) {
            return LocalDate.now().atTime(localTime);
        }
        return null;
    }

    @Named("detectCityName")
    static String getCityName(ReverseGeocoding reverseGeocoding) {
        if (StringUtils.isEmpty(reverseGeocoding.getCity())) {
            String locality = reverseGeocoding.getLocality();
            return StringUtils.substring(locality, 0, locality.indexOf(","));
        }
        return reverseGeocoding.getCity();
    }

}
