package tao.berich.getfundlist.domain;

import reactor.core.publisher.Flux;
import tao.berich.getfundlist.domain.model.Fund;

public interface FundService {
    Flux<Fund> getFunds();
}
