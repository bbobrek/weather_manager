package pl.bbobrek.weather.manager.domain.weather.result.details;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.bbobrek.weather.manager.domain.weather.result.RandomUUIDMapper;
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualHourlyConditions;

@Mapper(componentModel = "spring", uses = { PrecipitationMapper.class, NumberMapper.class, RandomUUIDMapper.class })
public interface HourlyWeatherDetailsMapper {


    @Mapping(target = "cloudCoverage", source = "cloudCoverage", qualifiedByName = {"numberMapper", "round"})
    @Mapping(target = "precipitationProbability", source = "precipitationProbability", qualifiedByName = {"numberMapper", "round"})
    @Mapping(target = "feelsLike", source = "feelsLike", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "humidity", source = "humidity", qualifiedByName = {"numberMapper", "round"})
    @Mapping(target = "pressure", source = "pressure", qualifiedByName = {"numberMapper", "round"})
    @Mapping(target = "temperature", source = "temp", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "timestamp", source = "datetimeEpoch")
    @Mapping(target = "windGust", source = "windGust", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "windSpeed", source = "windSpeed", qualifiedByName = {"numberMapper", "roundAndMultiply"})
    @Mapping(target = "windDegrees", source = "windDirection", qualifiedByName = {"numberMapper", "round"})
    @Mapping(target = "rain", source = ".", qualifiedByName = {"precipitationMapper", "rainValue"})
    @Mapping(target = "snow", source = ".", qualifiedByName = {"precipitationMapper", "snowValue"})
    @Mapping(target = "id", source = ".", qualifiedByName = {"randomUuIdMapper", "randomUuId"})
    HourlyWeatherDetails toModel(VisualHourlyConditions visualHourlyConditions);

}
