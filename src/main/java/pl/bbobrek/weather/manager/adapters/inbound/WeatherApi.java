package pl.bbobrek.weather.manager.adapters.inbound;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.bbobrek.weather.manager.domain.weather.VisualWeatherService;
import pl.bbobrek.weather.manager.models.weather.response.weather.WeatherResponse;

@RestController
@RequiredArgsConstructor
public class WeatherApi {

    private final VisualWeatherService weatherService;

    @GetMapping("/weather/{lat}/{lon}")
    public WeatherResponse getWeather(@PathVariable String lat, @PathVariable String lon) {
        return weatherService.produceResponse(lat, lon);
    }

}
