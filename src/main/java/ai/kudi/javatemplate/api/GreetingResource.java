package ai.kudi.javatemplate.api;

import ai.kudi.javatemplate.Metrics;
import ai.kudi.javatemplate.greeting.GreetingService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Dozzman
 * created on 28/09/2019
 */
@Path("/greet")
@Produces(MediaType.TEXT_PLAIN)
public final class GreetingResource {
    private final GreetingService greetingService;

    public GreetingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    public String greetDefault() {
        Metrics.requestsTotal.inc();
        final var requestTimer = Metrics.requestsDurationMillis.labels("/greet").startTimer();
        final var result = greetingService.greetDefault();
        requestTimer.observeDuration();
        return result;
    }

    @GET
    @Path("/{name}")
    public String greetWithName(@PathParam("name") final String name) {
        Metrics.requestsTotal.inc();
        final var requestTimer = Metrics.requestsDurationMillis.labels("/greet/{name}").startTimer();
        final var result = greetingService.greetWithName(name);
        requestTimer.observeDuration();
        return result;
    }
}
