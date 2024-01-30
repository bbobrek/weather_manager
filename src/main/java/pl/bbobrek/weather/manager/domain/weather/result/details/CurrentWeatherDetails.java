package pl.bbobrek.weather.manager.domain.weather.result.details;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class CurrentWeatherDetails {

    @Id
    private UUID id;

    private Integer cloudCoverage;
    private Integer feelsLike;
    private Integer humidity;
    private Integer precipitationProbability;
    private Integer pressure;
    private Integer rain;
    private Integer snow;
    private Integer temperature;

    private Instant timestamp;

    private String conditions;

    private Integer windDegrees;
    private Integer windGust;
    private Integer windSpeed;

    private String icon;

    @OneToOne
    @JoinColumn(name = "weather_result_id")
    private WeatherResult weatherResult;

    public long getTimestamp() {
        return timestamp.getEpochSecond();
    }
}
