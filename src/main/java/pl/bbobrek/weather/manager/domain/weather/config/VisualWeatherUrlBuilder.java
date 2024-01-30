package pl.bbobrek.weather.manager.domain.weather.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.bbobrek.weather.manager.config.VisualWeatherConfig;
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord;
import java.util.Map;

@Component
public class VisualWeatherUrlBuilder {

    private final ConnectionConfigRecord connectionConfigRecord;

    public VisualWeatherUrlBuilder(@Qualifier(VisualWeatherConfig.VISUAL_WEATHER_CONFIG_QUALIFIER) ConnectionConfigRecord connectionConfigRecord) {
        this.connectionConfigRecord = connectionConfigRecord;
    }

    public String build(String lat, String lon) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(connectionConfigRecord.url());
        connectionConfigRecord.params()
                .forEach(uriBuilder::queryParam);
        uriBuilder.queryParam("key", connectionConfigRecord.apiKey());
        return uriBuilder.buildAndExpand(Map.of("lat", lat, "lon", lon))
                .toUriString();
    }

}
