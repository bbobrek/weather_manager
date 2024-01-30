package pl.bbobrek.weather.manager.domain.geocoding;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "reverse_geocoding")
@Getter
@Setter
public class ReverseGeocoding {

    @Id
    private UUID id;

    private String latitude;
    private String longitude;

    private String city;
    private String locality;

}
