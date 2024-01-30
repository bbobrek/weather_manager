package pl.bbobrek.weather.manager.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocoding;

import java.util.List;
import java.util.UUID;

public interface ReverseGeocodingRepository extends JpaRepository<ReverseGeocoding, UUID> {

    @Query("SELECT rg FROM ReverseGeocoding rg WHERE rg.latitude = :latitude AND rg.longitude = :longitude")
    List<ReverseGeocoding> findAllByLatitudeAndLongitude(@Param("latitude") String latitude, @Param("longitude") String longitude);

}
