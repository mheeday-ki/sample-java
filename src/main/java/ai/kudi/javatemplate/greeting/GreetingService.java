package ai.kudi.javatemplate.greeting;

/**
 * @author Dozzman
 * created on 28/09/2019
 */
public class GreetingService {

    private final String name;

    public GreetingService(final String name) {
        this.name = name;
    }

    public String greetDefault() {
        return greet(this.name);
    }

    public String greetWithName(final String name) {
        return greet(name);
    }

    private String greet(final String name) {
        return "Hello, " + name + "!";
    }
}
