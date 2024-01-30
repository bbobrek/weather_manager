package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.daily;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetailsMapper;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetailsMapper;

@Mapper(componentModel = "spring",
        uses = {WeatherDetailsMapper.class, WindDetailsMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DailyConditionsMapper {

    @Mapping(target = "weather", source = ".")
    @Mapping(target = "wind", source = ".")
    @Mapping(target = "feelsLike.day", source = "feelsLike")
    @Mapping(target = "temperature.day", source = "temperature")
    @Mapping(target = "temperature.min", source = "temperatureMin")
    @Mapping(target = "temperature.max", source = "temperatureMax")
    DailyConditionsResponse toDto(DailyWeatherDetails dailyWeatherDetails);

}
