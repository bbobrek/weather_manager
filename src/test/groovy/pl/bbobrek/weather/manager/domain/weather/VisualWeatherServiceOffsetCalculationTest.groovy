package pl.bbobrek.weather.manager.domain.weather

import org.springframework.web.client.RestTemplate
import pl.bbobrek.weather.manager.domain.geocoding.ReverseGeocodingService
import pl.bbobrek.weather.manager.domain.geocoding.config.BigCloudDataUrlBuilder
import pl.bbobrek.weather.manager.domain.weather.VisualWeatherService
import pl.bbobrek.weather.manager.domain.weather.config.VisualWeatherUrlBuilder
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResult
import pl.bbobrek.weather.manager.domain.weather.result.WeatherResultService
import pl.bbobrek.weather.manager.domain.weather.util.DateProvider
import pl.bbobrek.weather.manager.models.weather.response.weather.WeatherResponseMapper
import spock.lang.Specification

import java.time.LocalDateTime

class VisualWeatherServiceOffsetCalculationTest extends Specification {

    def dateProvider = Mock(DateProvider)

    def visualWeatherService = new VisualWeatherService(Mock(RestTemplate), Mock(VisualWeatherUrlBuilder),
            Mock(WeatherResultService), Mock(WeatherResponseMapper), dateProvider, Mock(ReverseGeocodingService))

    def 'should correctly determine if creation date is out of offset'() {
        given:
        def weatherResult = new WeatherResult(creationDate: givenCreationDate)

        when:
        def result = visualWeatherService.isNotOlderThanOffset(weatherResult)

        then:
        1 * dateProvider.currentLocalDateTime() >> currentDate
        expectedResult == result

        where:
        givenCreationDate | currentDate || expectedResult
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 5, 14, 21) || false
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 3, 14, 21) || false
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 4, 13, 12) || false
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 4, 12, 56) || true
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 4, 13, 00) || false
        LocalDateTime.of(2023, 2, 4, 12, 33) | LocalDateTime.of(2023, 2, 4, 12, 59) || true
    }

}
