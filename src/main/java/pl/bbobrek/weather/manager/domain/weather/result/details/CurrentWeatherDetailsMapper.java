package pl.bbobrek.weather.manager.domain.weather.result.details;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.RandomUUIDMapper;
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualCurrentConditions;

@Mapper(componentModel = "spring", uses = { PrecipitationMapper.class, NumberMapper.class, RandomUUIDMapper.class})
public interface CurrentWeatherDetailsMapper {

    @Mapping(target = "temperature", source = "temp", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "feelsLike", source = "feelsLike", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "windSpeed", source = "windSpeed", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "timestamp", source = "datetimeEpoch")
    @Mapping(target = "windDegrees", source = "windDirection")
    @Mapping(target = "rain", source = ".", qualifiedByName = {"precipitationMapper", "rainValue"})
    @Mapping(target = "snow", source = ".", qualifiedByName = {"precipitationMapper", "snowValue"})
    @Mapping(target = "id", source = ".", qualifiedByName = {"randomUuIdMapper", "randomUuId"})
    CurrentWeatherDetails toModel(VisualCurrentConditions currentConditions);

}
