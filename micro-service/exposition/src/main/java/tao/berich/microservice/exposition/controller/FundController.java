package tao.berich.microservice.exposition.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.FundService;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;

@RestController
public class FundController {

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
}

