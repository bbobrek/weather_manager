package pl.bbobrek.weather.manager.domain.weather.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocoding;
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualWeatherResponse;
import pl.bbobrek.weather.manager.adapters.outbound.WeatherResultRepository;

import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherResultService {

    private final WeatherResultRepository weatherResultRepository;
    private final WeatherResultMapper weatherResultMapper;

    public Optional<WeatherResult> findLatest(String lat, String lon) {
        return weatherResultRepository.findAllByLatLon(lat, lon)
                .stream()
                .max(Comparator.comparing(WeatherResult::getCreationDate));
    }

    @Transactional
    public WeatherResult saveNewAndRemoveOldRecords(VisualWeatherResponse response, ReverseGeocoding reverseGeocoding) {
        removeAllRecords(response.getLatitude(), response.getLongitude());
        WeatherResult modelToSave = weatherResultMapper.toModel(response, reverseGeocoding);
        return weatherResultRepository.save(modelToSave);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void removeAllRecords(String lat, String lon) {
        weatherResultRepository.removeAllByLatLon(lat, lon);
    }

}
