package tao.berich.microservice.infra.fund;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tao.berich.microservice.domain.FundService;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.infra.fund.s3.aws.S3AwsClient;

@Service
public class FundServiceImpl implements FundService {
    private final S3AwsClient s3AwsClient;

    public FundServiceImpl(S3AwsClient s3AwsClient) {
        this.s3AwsClient = s3AwsClient;
    }

    @Override
    public Flux<Fund> getFunds() {
        //s3AwsClient.getFundList();
        return null;
    }
}
