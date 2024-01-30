package pl.bbobrek.weather.manager.domain.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocoding;
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocodingService;
import pl.bbobrek.weather.manager.domain.weather.config.VisualWeatherUrlBuilder;
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult;
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResultService;
import pl.bbobrek.weather.manager.domain.weather.util.DateProvider;
import pl.bbobrek.weather.manager.models.weather.response.weather.WeatherResponse;
import pl.bbobrek.weather.manager.models.weather.response.weather.WeatherResponseMapper;
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualWeatherResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VisualWeatherService {

    private final RestTemplate restTemplate;
    private final VisualWeatherUrlBuilder visualWeatherUrlBuilder;
    private final WeatherResultService weatherResultService;
    private final WeatherResponseMapper weatherResponseMapper;
    private final DateProvider dateProvider;
    private final ReverseGeocodingService reverseGeocodingService;

    public WeatherResponse produceResponse(String lat, String lon) {
        return weatherResultService.findLatest(lat, lon)
                .filter(this::isNotOlderThanOffset)
                .map(weatherResponseMapper::toDto)
                .orElseGet(() -> getWeatherResultAndSaveThem(lat, lon));
    }

    private WeatherResponse getWeatherResultAndSaveThem(String lat, String lon) {
        VisualWeatherResponse response = visualWeatherApiCall(lat, lon);
        ReverseGeocoding reverseGeocoding = reverseGeocodingService.getReverseGeocoding(lat, lon);
        WeatherResult savedResult = weatherResultService.saveNewAndRemoveOldRecords(response, reverseGeocoding);
        return Optional.ofNullable(savedResult)
                .map(weatherResponseMapper::toDto)
                .orElse(null);
    }

    protected boolean isNotOlderThanOffset(WeatherResult weatherResult) {
        LocalDateTime now = dateProvider.currentLocalDateTime();
        LocalDateTime creationDate = weatherResult.getCreationDate();
        return Duration.between(creationDate, now).toHours() < 1 && creationDate.getHour() == now.getHour();
    }

    private VisualWeatherResponse visualWeatherApiCall(String lat, String lon) {
        String url = visualWeatherUrlBuilder.build(lat, lon);
        ResponseEntity<VisualWeatherResponse> resultResponseEntity = restTemplate.getForEntity(url, VisualWeatherResponse.class);
        return resultResponseEntity.getBody();
    }

}
