package pl.bbobrek.weather.manager.domain.weather.config

import pl.bbobrek.weather.manager.domain.weather.config.VisualWeatherUrlBuilder
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord
import spock.lang.Specification

class VisualWeatherUrlBuilderTest extends Specification {

    private static final def URL = 'https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/{lat},{lon}/next9days'
    private static final def API_KEY = "32948234@#Rwe9u9wer"
    private static final def PARAMS = [
            unitGroup  : "metric",
            elements   : "sunriseEpoch,sunsetEpoch,datetime,datetimeEpoch,name,address,resolvedAddress,latitude,longitude,tempmax,tempmin,temp,feelslikemax,feelslikemin,feelslike,humidity,precip,precipprob,precipcover,preciptype,snow,snowdepth,windgust,windspeed,winddir,pressure,cloudcover,visibility,conditions,description,icon",
            iconSet    : "icons2",
            include    : "hours,days,current",
            contentType: "json",
            options    : "nonulls"
    ]

    def mockedVisualWeatherConfig = ConnectionConfigRecord.builder()
            .url(URL)
            .apiKey(API_KEY)
            .params(PARAMS)
            .build()

    def visualWeatherUrlBuilder = new VisualWeatherUrlBuilder(mockedVisualWeatherConfig)

    def 'should create correct url'() {
        given:
        def lat = "100"
        def lon = "150"

        when:
        def url = visualWeatherUrlBuilder.build(lat, lon)

        then:
        def latLonUrl = URL.replace('{lat}', lat).replace('{lon}', lon)
        url == "${latLonUrl}?unitGroup=${PARAMS['unitGroup']}&elements=${PARAMS['elements']}&iconSet=${PARAMS['iconSet']}&include=${PARAMS['include']}&contentType=${PARAMS['contentType']}&options=${PARAMS['options']}&key=${API_KEY}"
    }

}
