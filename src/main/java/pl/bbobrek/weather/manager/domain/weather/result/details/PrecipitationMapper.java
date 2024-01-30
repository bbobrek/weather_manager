package pl.bbobrek.weather.manager.domain.weather.result.details;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pl.bbobrek.weather.manager.models.weather.response.visual.PrecipitationConditions;

import java.util.List;

@Component
@Named("precipitationMapper")
public class PrecipitationMapper {

    @Named("rainValue")
    public int rainValue(PrecipitationConditions precipitationConditions) {
        List<String> preciptype = precipitationConditions.getPreciptype();
        if(CollectionUtils.isNotEmpty(preciptype) && CollectionUtils.containsAny(preciptype, "rain", "freezingrain")) {
            return precipitationConditions.getCalculatedPrecip();
        }
        return 0;
    }


    @Named("snowValue")
    public int snowValue(PrecipitationConditions precipitationConditions) {
        List<String> preciptype = precipitationConditions.getPreciptype();
        if(CollectionUtils.isNotEmpty(preciptype) && preciptype.contains("snow")) {
            return precipitationConditions.getCalculatedPrecip();
        }
        return 0;
    }

}
