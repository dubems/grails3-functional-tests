package gorm


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration(applicationClass = Application)
@Rollback
class ProductSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test unique constraint persistence"() {
        when:"A new unique product is created"
            def p = new Product(isbn: '123')

        then:"It is valid"
            p.validate()
            !p.hasErrors()

        when:"When it is persisted"
            p.save(flush:true)

        then:"The object is saved correctly"
            Product.count() == 1

        when:"Another product is created that violates the unique constraint"
            def p2 = new Product(isbn: '123')

        then:"The other product does not validate"
            !p2.validate()
            Product.count() == 1
    }
}
