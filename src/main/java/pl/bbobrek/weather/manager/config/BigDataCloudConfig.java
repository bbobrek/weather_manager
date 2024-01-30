package pl.bbobrek.weather.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord;

@Configuration
public class BigDataCloudConfig {

    public static final String BIG_DATA_CLOUD_CONFIG_QUALIFIER = "bigDataCloudConfigRecord";

    @Value("${big.data.cloud.url}")
    private String url;

    @Value("${big.data.cloud.key}")
    private String key;

    @Bean(BIG_DATA_CLOUD_CONFIG_QUALIFIER)
    public ConnectionConfigRecord bigDataCloudConfigRecord() {
        return ConnectionConfigRecord.builder()
                .url(url)
                .apiKey(key)
                .build();
    }

}
