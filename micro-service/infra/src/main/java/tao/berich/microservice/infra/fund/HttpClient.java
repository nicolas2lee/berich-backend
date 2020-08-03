package tao.berich.microservice.infra.fund;

import org.springframework.web.reactive.function.client.WebClient;

public interface HttpClient {
    static WebClient buildWebClient(String baseUrl){
        return WebClient.builder().baseUrl(baseUrl)
                .build();
    }
}
