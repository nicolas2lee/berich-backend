package tao.berich.microservice.exposition.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FundController {

    @GetMapping("/funds")
    public Mono<String> getFunds() {
        return Mono.just("Getting back..");
    }

    @GetMapping("/funds/{id}")
    public Mono<String> getFundDetailById(@PathVariable(name = "id") String fundId) {
        return Mono.just("Posting back..");
    }


}

