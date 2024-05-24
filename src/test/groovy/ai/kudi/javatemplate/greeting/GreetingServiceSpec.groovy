package ai.kudi.javatemplate.greeting

import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author Dozzman* created on 28/09/2019
 */
class GreetingServiceSpec extends Specification {
    @Unroll
    def "test greeting #name"() {
        setup:
        def svc = new GreetingService("dorian")


        expect:
        svc.greetWithName(name) == "Hello, " + name + "!"


        where:
        name      | _
        "dorian"  | _
        "pelumi"  | _
        "abayomi" | _
        "ope"     | _
        "lara"    | _
        "mesh"    | _
        "david"   | _
        "bukola"  | _
    }
}
