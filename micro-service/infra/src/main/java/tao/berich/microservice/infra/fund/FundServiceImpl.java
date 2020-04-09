package tao.berich.microservice.infra.fund;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.FundService;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;
import tao.berich.microservice.infra.fund.realtime.sina.client.SinaClient;
import tao.berich.microservice.infra.fund.s3.aws.S3AwsClient;

@Service
public class FundServiceImpl implements FundService {

    private final S3AwsClient s3AwsClient;
    private final SinaClient sinaClient;

    private static final String FUND_BUCKET_NAME = "fund-list";
    private static final String FUND_KEY_NAME= "fund.json";

    public FundServiceImpl(S3AwsClient s3AwsClient, SinaClient sinaClient) {
        this.s3AwsClient = s3AwsClient;
        this.sinaClient = sinaClient;
    }

    @Override
    public Flux<Fund> getFunds() {
        return Flux.fromStream(s3AwsClient.getFundList(FUND_BUCKET_NAME, FUND_KEY_NAME));
    }

    @Override
    public Mono<FundDetail> getFundDetailByCode(String fundCode) {
        return sinaClient.getFundDetailByFundCode(fundCode);
    }
}
