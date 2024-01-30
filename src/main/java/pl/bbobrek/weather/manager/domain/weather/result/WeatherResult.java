package pl.bbobrek.weather.manager.domain.weather.result;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetails;
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class WeatherResult {

    @Id
    private UUID id;
    private String latitude;
    private String longitude;
    private LocalDateTime creationDate;
    private Instant sunrise;
    private Instant sunset;
    private String city;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "weatherResult")
    private CurrentWeatherDetails currentWeatherDetails;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "weather_result_id")
    private List<DailyWeatherDetails> dailyWeatherDetails;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    @JoinColumn(name = "weather_result_id")
    private List<HourlyWeatherDetails> hourlyWeatherDetails;

    public long getSunrise() {
        return sunrise.getEpochSecond();
    }

    public long getSunset() {
        return sunset.getEpochSecond();
    }

    public void setCurrentWeatherDetails(CurrentWeatherDetails currentWeatherDetails) {
        currentWeatherDetails.setWeatherResult(this);
        this.currentWeatherDetails = currentWeatherDetails;
    }
}
