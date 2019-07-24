Run with `mvn clean compile exec:java`

Metrics are served on http://localhost:8081/metrics.

If jvm metrics are enabled (line 16 in hr.tjakopan.vertx.micrometer.Launcher) getting the metrics snapshot will fail and
return https status code 500.

The cause of the error is line 99 in io.vertx.micrometer.impl.MetricsServiceImpl which is trying to cast CumulativeFunctionCounter
to Counter.
The following exception is caught in line 126 in RoutingContextImplBase - 
`java.lang.ClassCastException: io.micrometer.core.instrument.cumulative.CumulativeFunctionCounter cannot be cast to io.micrometer.core.instrument.Counter`.
