package pl.bbobrek.weather.manager.models.weather.response.weather.common;

import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetails;

@Mapper(componentModel = "spring")
public interface WindDetailsMapper {

    @Mapping(target = "degrees", source = "windDegrees")
    @Mapping(target = "gust", source = ".", qualifiedByName = "windGustMappingNullSafe")
    @Mapping(target = "speed", source = "windSpeed")
    WindDetails toDto(CurrentWeatherDetails currentWeatherDetails);

    @Mapping(target = "degrees", source = "windDegrees")
    @Mapping(target = "gust", source = "windGust")
    @Mapping(target = "speed", source = "windSpeed")
    WindDetails toDto(DailyWeatherDetails dailyWeatherDetails);

    @Mapping(target = "degrees", source = "windDegrees")
    @Mapping(target = "gust", source = "windGust")
    @Mapping(target = "speed", source = "windSpeed")
    WindDetails toDto(HourlyWeatherDetails hourlyWeatherDetails);

    @Named("windGustMappingNullSafe")
    default Integer windGustNullSafeMapping(CurrentWeatherDetails currentWeatherDetails) {
        return ObjectUtils.firstNonNull(currentWeatherDetails.getWindGust(), currentWeatherDetails.getWindSpeed());
    }

}
