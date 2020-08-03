package tao.berich.microservice.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;
import tao.berich.microservice.domain.model.FundDetailHistory;

public interface FundService {
    Flux<Fund> getFunds();

    Mono<FundDetail> getFundDetailByCode(String fundCode);

    Flux<FundDetailHistory> getFundDetailHistoryByCode(String fundCode);
}
