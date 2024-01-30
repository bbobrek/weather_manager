package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.hourly;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetailsMapper;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetailsMapper;

@Mapper(componentModel = "spring",
        uses = {WeatherDetailsMapper.class, WindDetailsMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HourlyConditionsMapper {

    @Mapping(target = "weather", source = ".")
    @Mapping(target = "wind", source = ".")
    HourlyConditionsResponse toDto(HourlyWeatherDetails hourlyWeatherDetails);

}
