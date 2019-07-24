package hr.tjakopan.vertx.micrometer;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;

public class Launcher extends io.vertx.core.Launcher {
  public static void main(String[] args) {
    new Launcher().dispatch(args);
  }

  @Override
  public void beforeStartingVertx(final VertxOptions options) {
    final MetricsOptions metricsOptions = new MicrometerMetricsOptions()
//      .setJvmMetricsEnabled(true)
      .setRegistryName("my-registry")
      .setMicrometerRegistry(new SimpleMeterRegistry())
      .setEnabled(true);
    options.setMetricsOptions(metricsOptions);
  }
}
