package functionaltests

import grails.test.mixin.integration.Integration

import geb.spock.*

@Integration
class UriMatchingInterceptorFunctionalSpec extends GebSpec {

    void "Test that an interceptor that redirects works"() {
        when:
        go '/interceptorDemo/one'

        then:
        $().text() == 'interceptor did match for the one action'

        when:
        go '/interceptorDemo/two'

        then:
        $().text() == 'interceptor did not match for the two action'
    }
}
