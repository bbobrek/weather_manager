package pl.bbobrek.weather.manager.models.geocoding;

import lombok.Data;

@Data
public class ReverseGeocodingResponse {

    private String city;
    private String locality;

}
