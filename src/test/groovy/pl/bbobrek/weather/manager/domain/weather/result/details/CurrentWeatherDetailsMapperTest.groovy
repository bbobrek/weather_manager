package pl.bbobrek.weather.manager.domain.weather.result.details

import pl.bbobrek.weather.manager.domain.weather.result.RandomUUIDMapper
import pl.bbobrek.weather.manager.domain.weather.result.details.CurrentWeatherDetailsMapperImpl
import pl.bbobrek.weather.manager.domain.weather.result.details.NumberMapper
import pl.bbobrek.weather.manager.domain.weather.result.details.PrecipitationMapper
import pl.bbobrek.weather.manager.models.weather.response.visual.VisualCurrentConditions
import spock.lang.Specification

import java.time.Instant

class CurrentWeatherDetailsMapperTest extends Specification {

    def currentWeatherDetailsMapper = new CurrentWeatherDetailsMapperImpl(numberMapper: new NumberMapper(), precipitationMapper: new PrecipitationMapper(), randomUUIDMapper: new RandomUUIDMapper())

    def 'should correctly map visual current conditions to entity'() {
        given:
        def visualCurrentConditions = new VisualCurrentConditions(
                datetimeEpoch: Instant.ofEpochMilli(123123123),
                temp: 9.34,
                feelsLike: 45.33,
                humidity: 23.12,
                precip: 1.43,
                pressure: 44.123,
                precipitationProbability: 98.45,
                cloudCoverage: 3.67,
                windGust: 98.45,
                windSpeed: 123.33,
                windDirection: 4.123,
                sunset: Instant.ofEpochMilli(123123123123123),
                sunrise: Instant.ofEpochMilli(98787),
                preciptype: ['snow']
        )

        when:
        def result = currentWeatherDetailsMapper.toModel(visualCurrentConditions)

        then:
        result.timestamp == visualCurrentConditions.getDatetimeEpoch().getEpochSecond()
        result.cloudCoverage == visualCurrentConditions.cloudCoverage.intValue()
        result.feelsLike == (visualCurrentConditions.feelsLike * 10).intValue()
        result.humidity == visualCurrentConditions.humidity.intValue()
        result.precipitationProbability == visualCurrentConditions.precipitationProbability.intValue()
        result.pressure == visualCurrentConditions.pressure.intValue()
        result.snow == (Math.round(visualCurrentConditions.precip) * 10).intValue()
        result.temperature == (visualCurrentConditions.temp * 10).intValue()
        result.windDegrees == visualCurrentConditions.windDirection.intValue()
        result.windGust == visualCurrentConditions.windGust.intValue()
        result.windSpeed == (visualCurrentConditions.windSpeed * 10).intValue()
    }

}
