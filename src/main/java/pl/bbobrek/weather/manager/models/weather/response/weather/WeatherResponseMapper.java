package pl.bbobrek.weather.manager.models.weather.response.weather;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.current.CurrentConditionsMapper;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.daily.DailyConditionsMapper;
import pl.bbobrek.weather.manager.models.weather.response.weather.conditions.hourly.HourlyConditionsMapper;

@Mapper(componentModel = "spring",
        uses = {CurrentConditionsMapper.class, DailyConditionsMapper.class, HourlyConditionsMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WeatherResponseMapper {

    @Mapping(target = "current", source = "currentWeatherDetails")
    @Mapping(target = "daily", source = "dailyWeatherDetails")
    @Mapping(target = "hourly", source = "hourlyWeatherDetails")
    WeatherResponse toDto(WeatherResult weatherResult);

}
