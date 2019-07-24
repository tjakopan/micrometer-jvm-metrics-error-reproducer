package hr.tjakopan.vertx.micrometer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.micrometer.MetricsService;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    final MetricsService metricsService = MetricsService.create(vertx);
    final Router router = Router.router(vertx);
    router.get("/metrics").handler(routingContext -> {
      final JsonObject metrics = metricsService.getMetricsSnapshot();
      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json")
        .end(metrics.encodePrettily());
    });

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8081, ar -> {
        if (ar.succeeded()) {
          startPromise.complete();
        } else {
          startPromise.fail(ar.cause());
        }
      });
  }

  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {
    stopPromise.complete();
  }
}
