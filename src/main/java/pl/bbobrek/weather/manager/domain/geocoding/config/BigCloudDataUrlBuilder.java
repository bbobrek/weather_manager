package pl.bbobrek.weather.manager.domain.geocoding.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pl.bbobrek.weather.manager.config.BigDataCloudConfig;
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord;

@Component
public class BigCloudDataUrlBuilder {

    private static final String LOCALITY_LANGUAGE = "pl";

    private final ConnectionConfigRecord bigDataCloudConfigRecord;

    public BigCloudDataUrlBuilder(@Qualifier(BigDataCloudConfig.BIG_DATA_CLOUD_CONFIG_QUALIFIER) ConnectionConfigRecord bigDataCloudConfigRecord) {
        this.bigDataCloudConfigRecord = bigDataCloudConfigRecord;
    }

    public String build(String lat, String lon) {
        return UriComponentsBuilder.fromHttpUrl(bigDataCloudConfigRecord.url())
                .queryParam("latitude", lat)
                .queryParam("longitude", lon)
                .queryParam("key", bigDataCloudConfigRecord.apiKey())
                .queryParam("localityLanguage", LOCALITY_LANGUAGE)
                .toUriString();
    }

}
