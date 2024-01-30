package pl.bbobrek.weather.manager.domain.weather.config

import pl.bbobrek.weather.manager.domain.geocoding.config.BigCloudDataUrlBuilder
import pl.bbobrek.weather.manager.models.weather.config.ConnectionConfigRecord
import spock.lang.Specification

class BigCloudDataUrlBuilderTest extends Specification {

    private static final def URL = 'https://api.bigdatacloud.net/data/reverse-geocode'
    private static final def API_KEY = "asidjiasjdioasjdiasjdoiajsdoijasd"

    def mockedBigDataCloudConfig = ConnectionConfigRecord.builder()
            .url(URL)
            .apiKey(API_KEY)
            .build()

    def bigCloudDataUrlBuilder = new BigCloudDataUrlBuilder(mockedBigDataCloudConfig)

    def 'should create correct url'() {
        given:
        def lat = "100"
        def lon = "150"

        when:
        def url = bigCloudDataUrlBuilder.build(lat, lon)

        then:
        url == "${URL}?latitude=${lat}&longitude=${lon}&key=${API_KEY}&localityLanguage=pl"
    }

}
