package pl.bbobrek.weather.manager.domain.geocoding;

import org.mapstruct.Mapper;
import pl.bbobrek.weather.manager.models.geocoding.ReverseGeocodingResponse;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ReverseGeocodingMapper {

    default ReverseGeocoding toModelGenerateId(ReverseGeocodingResponse response, String latitude, String longitude) {
        ReverseGeocoding reverseGeocoding = toModel(response);
        reverseGeocoding.setLatitude(latitude);
        reverseGeocoding.setLongitude(longitude);
        reverseGeocoding.setId(UUID.randomUUID());
        return reverseGeocoding;
    }

    ReverseGeocoding toModel(ReverseGeocodingResponse response);

}
