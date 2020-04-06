package tao.berich.getfundlist.infra.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tao.berich.getfundlist.domain.FundService;
import tao.berich.getfundlist.domain.model.Fund;

import java.util.function.Function;

@Service("fundService")
public class FundServiceImpl implements FundService {
    private final static String SHANGHAI_STOCK_EXCHANGE_URL = "http://www.sse.com.cn";
    private final static String URI = "/js/common/ssesuggestfunddata.js;waee5efe68e2da70cd";

    @Override
    public Flux<Fund> getFunds() {
        final WebClient webClient = WebClient.create(SHANGHAI_STOCK_EXCHANGE_URL);

        Function<Flux<String>, Flux<Fund>> transformerFunction = s -> s.filter(x-> x.startsWith("_t.push"))
                .map(this::findContentInsideBracket)
                .map(this::mapToFund)
                ;

        return webClient.get()
                .uri(URI)
                .retrieve()
                .bodyToFlux(String.class)
                .transform(transformerFunction)
                ;
    }

    String findContentInsideBracket(String s) {
        return s.substring(s.indexOf("{")+1,s.indexOf("}"));
    }

    Fund mapToFund(String s){
        final String[] groups = s.split(",");
        return new Fund(extractFieldValue(groups[0]), extractFieldValue(groups[1]), extractFieldValue(groups[2]));
    }

    private String extractFieldValue(String s){
        return s.split(":")[1].replaceAll("\"", "");
    }
}
