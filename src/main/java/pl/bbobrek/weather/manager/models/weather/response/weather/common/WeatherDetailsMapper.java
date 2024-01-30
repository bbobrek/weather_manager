package pl.bbobrek.weather.manager.models.weather.response.weather.common;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.WeatherIcon;
import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface WeatherDetailsMapper {

    @Mapping(target = "main", source = "conditions")
    @Mapping(target = "description", source = "conditions")
    @Mapping(target = "id", source = "icon", qualifiedByName = "iconIdMapping")
    WeatherDetails toDto(CurrentWeatherDetails currentWeatherDetails);

    @Mapping(target = "main", source = "conditions")
    @Mapping(target = "id", source = "icon", qualifiedByName = "iconIdMapping")
    WeatherDetails toDto(DailyWeatherDetails dailyWeatherDetails);

    @Mapping(target = "main", source = "conditions")
    @Mapping(target = "description", source = "conditions")
    @Mapping(target = "id", source = "icon", qualifiedByName = "iconIdMapping")
    WeatherDetails toDto(HourlyWeatherDetails hourlyWeatherDetails);

    @Named("iconIdMapping")
    default Integer mapToIconId(String icon) {
        return Arrays.stream(WeatherIcon.values())
                .filter(enumValue -> StringUtils.equals(enumValue.getProviderIconName(), icon))
                .findFirst()
                .map(WeatherIcon::getId)
                .orElse(WeatherIcon.CLEAR_SKY.getId());
    }

}
