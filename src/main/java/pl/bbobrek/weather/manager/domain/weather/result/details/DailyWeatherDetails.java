package pl.bbobrek.weather.manager.domain.weather.result.details;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DailyWeatherDetails {

    @Id
    private UUID id;

    private Instant timestamp;
    private Integer cloudCoverage;
    private Integer feelsLike;
    private Integer humidity;
    private Integer pressure;
    private Integer rain;
    private Integer snow;
    private Integer temperature;
    private Integer temperatureMax;
    private Integer temperatureMin;
    private Integer windDegrees;
    private Integer windGust;
    private Integer windSpeed;
    private String conditions;
    private String description;
    private String icon;

    public long getTimestamp() {
        return timestamp.getEpochSecond();
    }
}
