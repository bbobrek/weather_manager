package pl.bbobrek.weather.manager.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "visual.weather")
public class VisualWeatherConfig {

    public static final String VISUAL_WEATHER_CONFIG_QUALIFIER = "visualWeatherConfigRecord";

    @Value("${visual.weather.url}")
    private String url;

    @Value("${visual.weather.key}")
    private String key;

    @Setter
    private Map<String, String> params;

    @Bean(VISUAL_WEATHER_CONFIG_QUALIFIER)
    public ConnectionConfigRecord visualWeatherConfigRecord() {
        return ConnectionConfigRecord.builder()
                .url(url)
                .apiKey(key)
                .params(params)
                .build();
    }

}
