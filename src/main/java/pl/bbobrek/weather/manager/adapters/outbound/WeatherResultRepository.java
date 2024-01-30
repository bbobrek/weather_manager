package pl.bbobrek.weather.manager.adapters.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult;

import java.util.List;

public interface WeatherResultRepository extends JpaRepository<WeatherResult, Long> {

    @Query("SELECT wr FROM WeatherResult wr " +
            "WHERE wr.latitude = :lat AND wr.longitude = :lon")
    List<WeatherResult> findAllByLatLon(String lat, String lon);

    @Query("DELETE FROM WeatherResult wr WHERE wr.latitude = :lat AND wr.longitude = :lon")
    @Modifying
    void removeAllByLatLon(String lat, String lon);

}
