package tao.berich.getfundlist.exposition;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;
import tao.berich.getfundlist.domain.FundService;
import tao.berich.getfundlist.domain.model.Fund;
import tao.berich.getfundlist.inject.SpringInjectConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetFundListFunction implements RequestHandler<Object, GatewayResponse> {

   public GatewayResponse handleRequest(final Object input, final Context context) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(SpringInjectConfig.class);
        final FundService fundService = (FundService) springContext.getBean("fundService");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        final Flux<Fund> fundFlux = fundService.getFunds();
        final List<Fund> fundList = fundFlux.collect(Collectors.toList()).block();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           final String jsonString = objectMapper.writeValueAsString(fundList);
           return new GatewayResponse(jsonString, headers, 200);
        } catch (JsonProcessingException e) {
           return new GatewayResponse("Json serialization error", headers, 500);
        }

   }

}
