package ai.kudi.javatemplate;


import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

/**
 * @author Dozzman
 * created on 27/01/2020
 */
public class Metrics {
    public static final Counter requestsTotal = Counter.build()
        .name("requests_total")
        .help("Total number of requests")
        .register();

    public static final Histogram requestsDurationMillis = Histogram.build()
        .name("requests_duration_millis")
        .help("Duration of request")
        .labelNames("path")
        .register();
}
