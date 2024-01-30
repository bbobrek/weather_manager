package pl.bbobrek.weather.manager.domain.weather.result

import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocoding
import pl.bbobrek.weather.manager.domain.weather.result.RandomUUIDMapper
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResultMapperImpl
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResultService
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetailsMapperImpl
import pl.bbobrek.weather.manager.domain.weather.result.details.DailyWeatherDetailsMapperImpl
import pl.bbobrek.weather.manager.domain.weather.result.details.HourlyWeatherDetailsMapperImpl
import pl.bbobrek.weather.manager.domain.weather.result.details.NumberMapper
import pl.bbobrek.weather.manager.domain.weather.result.details.PrecipitationMapper
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualCurrentConditions
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualWeatherResponse
import pl.bbobrek.weather.manager.adapters.outbound.WeatherResultRepository
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime

class WeatherResultServiceTest extends Specification {

    def weatherResultRepository = Mock(WeatherResultRepository)
    def weatherResultMapper = new WeatherResultMapperImpl(
            new CurrentWeatherDetailsMapperImpl(numberMapper: new NumberMapper(), precipitationMapper: new PrecipitationMapper(), randomUUIDMapper: new RandomUUIDMapper()),
            new DailyWeatherDetailsMapperImpl(numberMapper: new NumberMapper(), precipitationMapper: new PrecipitationMapper(), randomUUIDMapper: new RandomUUIDMapper()),
            new HourlyWeatherDetailsMapperImpl(numberMapper: new NumberMapper(), precipitationMapper: new PrecipitationMapper(), randomUUIDMapper: new RandomUUIDMapper()),
            new RandomUUIDMapper()
    )

    def weatherResultService = new WeatherResultService(weatherResultRepository, weatherResultMapper)

    def 'should get only latest result from all available for given coordinates'() {
        given:
        def lat = "100"
        def lon = "150"
        def targetUuid = UUID.randomUUID()
        def allWeatherResults = [
                new WeatherResult(id: UUID.randomUUID(), creationDate: LocalDateTime.of(2023, 02, 02, 11, 23)),
                new WeatherResult(id: targetUuid, creationDate: LocalDateTime.of(2023, 02, 02, 11, 54))
        ]

        when:
        def result = weatherResultService.findLatest(lat, lon)

        then:
        1 * weatherResultRepository.findAllByLatLon(lat, lon) >> allWeatherResults
        result.isPresent()
        result.get().id == targetUuid
    }

    def 'should remove all old entries, save new and map to model based on responses'() {
        given:
        def visualWeatherResponse = new VisualWeatherResponse(
                latitude: "100", longitude: "150",
                days: [],
                currentConditions: new VisualCurrentConditions(
                        sunrise: Instant.ofEpochMilli(123123123),
                        sunset: Instant.ofEpochMilli(246246246),
                        temp: 89.12,
                        feelsLike: 45.33,
                        windSpeed: 123.33
                )
        )
        def reverseGeocodingResponse = new ReverseGeocoding(city: "Warszawa")

        def existedRecords = [
                new WeatherResult(), new WeatherResult(), new WeatherResult()
        ]

        when:
        weatherResultService.saveNewAndRemoveOldRecords(visualWeatherResponse, reverseGeocodingResponse)

        then:
        1 * weatherResultRepository.findAllByLatLon(visualWeatherResponse.getLatitude(), visualWeatherResponse.getLongitude()) >> existedRecords
        3 * weatherResultRepository.delete(_)
        1 * weatherResultRepository.save({
            assert it.latitude == visualWeatherResponse.latitude
            assert it.longitude == visualWeatherResponse.longitude
            assert it.city == reverseGeocodingResponse.city
            assert it.sunrise == visualWeatherResponse.currentConditions.sunrise.getEpochSecond()
            assert it.sunset == visualWeatherResponse.currentConditions.sunset.getEpochSecond()
        })
    }

}
