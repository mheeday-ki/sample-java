package ai.kudi.javatemplate;

import ai.kudi.javatemplate.api.GreetingResource;
import ai.kudi.javatemplate.greeting.GreetingService;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author Dozzman
 * created on 2019-09-28
 */
public class App extends Application<AppConfiguration> {
    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    /**
     * Returns the name of the application.
     *
     * @return the application's name
     */
    @Override
    public String getName() {
        return "app";
    }

    /**
     * Initializes the application bootstrap.
     *
     * @param bootstrap the application bootstrap
     */
    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(true)));
    }

    /**
     * When the application runs, this is called after the Bundles are run. Override it to add
     * providers, resources, etc. for your application.
     *
     * @param configuration the parsed Configuration object
     * @param environment   the application's Environment
     */
    @Override
    public void run(final AppConfiguration configuration,
                    final Environment environment) {
        // suppressing unused since this definition acts as a placeholder
        @SuppressWarnings("unused") final var client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration())
            .build(getName());

        // setup metrics
        DefaultExports.initialize();
        final var greetingService = new GreetingService(configuration.getName());
        environment.jersey().register(new GreetingResource(greetingService));
        environment.getApplicationContext().addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
    }
}
