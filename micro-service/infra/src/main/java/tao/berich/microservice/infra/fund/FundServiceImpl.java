package tao.berich.microservice.infra.fund;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.FundService;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;
import tao.berich.microservice.domain.model.FundDetailHistory;
import tao.berich.microservice.infra.fund.entity.FundEntity;
import tao.berich.microservice.infra.fund.history.sina.client.SinaHistoryClient;
import tao.berich.microservice.infra.fund.realtime.sina.client.SinaRealTimeClient;
import tao.berich.microservice.infra.fund.repository.FundRepository;

import java.util.stream.Stream;

@Service
public class FundServiceImpl implements FundService {

    private final SinaRealTimeClient sinaRealTimeClient;
    private final SinaHistoryClient sinaHistoryClient;
    private final FundRepository fundRepository;


    public FundServiceImpl(SinaRealTimeClient sinaRealTimeClient, SinaHistoryClient sinaHistoryClient, FundRepository fundRepository) {
        this.sinaRealTimeClient = sinaRealTimeClient;
        this.sinaHistoryClient = sinaHistoryClient;
        this.fundRepository = fundRepository;
    }

    @Override
    public Flux<Fund> getFunds() {
        final Stream<Fund> fundStream = fundRepository.findAll().stream()
                .map(FundEntity::toDomain);
        return Flux.fromStream(fundStream);
        //return Flux.fromStream(s3AwsClient.getFundList(FUND_BUCKET_NAME, FUND_KEY_NAME));
    }

    @Override
    public Mono<FundDetail> getFundDetailByCode(String fundCode) {
        return sinaRealTimeClient.getFundDetailByFundCode(fundCode);
    }

    @Override
    public Flux<FundDetailHistory> getFundDetailHistoryByCode(String fundCode) {
        return sinaHistoryClient.getFundDetailByFundCode(fundCode);
    }
}
