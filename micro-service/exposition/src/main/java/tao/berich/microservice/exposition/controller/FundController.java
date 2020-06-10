package tao.berich.microservice.exposition.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.FundService;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;

import java.util.List;

@RestController
public class FundController {

    private static final Logger LOG = LoggerFactory.getLogger(FundController.class);

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping("/funds")
    public Flux<Fund> getFunds() {
        return fundService.getFunds();
    }

    @GetMapping("/funds/{fundCode}")
    public Mono<FundDetail> getFundDetailById(@PathVariable(name = "fundCode") String fundCode) {
        return fundService.getFundDetailByCode(fundCode);
    }

    @GetMapping("/")
    public Mono<String> testOauth2(ServerHttpRequest request) {
        final List<String> strings = request.getHeaders().get("x-b3-traceid");
        final String traceHeader = String.join(",", strings);
        LOG.info(traceHeader);
        System.out.println("hello");
        return Mono.just("hello");
    }
}

