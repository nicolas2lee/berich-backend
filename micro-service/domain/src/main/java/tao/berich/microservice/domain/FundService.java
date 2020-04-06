package tao.berich.microservice.domain;

import reactor.core.publisher.Flux;
import tao.berich.microservice.domain.model.Fund;

public interface FundService {
    Flux<Fund> getFunds();
}
