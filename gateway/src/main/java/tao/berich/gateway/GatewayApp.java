package tao.berich.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Hooks;

import java.util.UUID;

@SpringBootApplication
public class GatewayApp {

    private static final String HTTP_HEADER_PREFIX = "GATEWAY";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory tokenRelay) {
        return builder.routes()
                .route("servlet-app", r -> r.path("/profile", "/")
                                .filters(f -> f.filter(tokenRelay.apply())
                                        .addRequestHeader("X-B3-TraceId", HTTP_HEADER_PREFIX+UUID.randomUUID().toString())
                                        .addRequestHeader("X-B3-SpanId", HTTP_HEADER_PREFIX+UUID.randomUUID().toString())
                                )
                                //.filters(f -> f.filter(tokenRelay.apply()))
                                .uri("http://localhost:8090"))

                .build();
    }

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(GatewayApp.class, args);
    }
}
