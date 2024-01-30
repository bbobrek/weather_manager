package pl.bbobrek.weather.manager.models.weather.response.visual;

import java.util.List;

public interface PrecipitationConditions {

    List<String> getPreciptype();
    Double getPrecip();

    default Integer getCalculatedPrecip() {
        if (getPrecip() != null) {
            return (int) Math.round(getPrecip()) * 10;
        }
        return 0;
    }

}
