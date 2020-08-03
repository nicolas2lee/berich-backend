package tao.berich.microservice.infra.fund.history.sina.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tao.berich.microservice.domain.model.FundDetailHistory;
import tao.berich.microservice.infra.fund.HttpClient;

@Component
public class SinaHistoryClient implements HttpClient {
    private final WebClient webClient;

    SinaHistoryClient() {
        this.webClient = HttpClient.buildWebClient("http://money.finance.sina.com.cn/");
    }

    public Flux<FundDetailHistory> getFundDetailByFundCode(String fundCode) {
        final String uri = "/quotes_service/api/json_v2.php/CN_MarketData.getKLineData";
        return webClient.get().uri(uriBuilder -> uriBuilder.path(uri)
                .queryParam("symbol", String.format("sh%s", fundCode))
                .queryParam("scale", 60) //each hour
                .queryParam("ma", "no")
                .queryParam("datalen", 1024) //max 1024 data node
                .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::buildHistory)
                .flatMapMany(Flux::fromArray);
    }

    private FundDetailHistory[] buildHistory(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, FundDetailHistory[].class);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new FundDetailHistory[]{};
        }
    }
}


