package pl.bbobrek.weather.manager.domain.geocoding;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.bbobrek.weather.manager.adapters.outbound.ReverseGeocodingRepository;
import pl.bbobrek.weather.manager.domain.geocoding.config.BigCloudDataUrlBuilder;
import pl.bbobrek.weather.manager.models.geocoding.ReverseGeocodingResponse;

@Component
@RequiredArgsConstructor
public class ReverseGeocodingService {

    private final RestTemplate restTemplate;
    private final BigCloudDataUrlBuilder bigCloudDataUrlBuilder;
    private final ReverseGeocodingRepository reverseGeocodingRepository;
    private final ReverseGeocodingMapper reverseGeocodingMapper;

    @Transactional
    public ReverseGeocoding getReverseGeocoding(String latitude, String longitude) {
        return reverseGeocodingRepository.findAllByLatitudeAndLongitude(latitude, longitude)
                .stream()
                .findFirst()
                .orElseGet(() -> fetchAndSaveReverseGeocoding(latitude, longitude));
    }

    private ReverseGeocoding fetchAndSaveReverseGeocoding(String latitude, String longitude) {
        ReverseGeocodingResponse response = reverseGeocodingResponse(latitude, longitude);
        ReverseGeocoding model = reverseGeocodingMapper.toModelGenerateId(response, latitude, longitude);
        reverseGeocodingRepository.save(model);
        return model;
    }

    private ReverseGeocodingResponse reverseGeocodingResponse(String lat, String lon) {
        String url = bigCloudDataUrlBuilder.build(lat, lon);
        ResponseEntity<ReverseGeocodingResponse> result = restTemplate.getForEntity(url, ReverseGeocodingResponse.class);
        return result.getBody();
    }

}
