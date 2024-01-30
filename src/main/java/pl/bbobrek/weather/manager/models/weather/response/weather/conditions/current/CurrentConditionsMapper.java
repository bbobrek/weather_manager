package pl.bbobrek.weather.manager.models.weather.response.weather.conditions.current;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetails;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WeatherDetailsMapper;
import pl.bbobrek.weather.manager.models.weather.response.weather.common.WindDetailsMapper;

@Mapper(componentModel = "spring",
        uses = {WeatherDetailsMapper.class, WindDetailsMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CurrentConditionsMapper {

    @Mapping(target = "weather", source = ".")
    @Mapping(target = "wind", source = ".")
    CurrentConditionsResponse toDto(CurrentWeatherDetails currentWeatherDetails);

}
