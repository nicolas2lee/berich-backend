package tao.berich.microservice.infra.fund.s3.aws;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.infra.fund.dto.FundDto;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Component
public class S3AwsClient {

    private static final long API_CALL_TIMEOUT = 60;
    private static final long API_CALL_ATTEMPT_TIMEOUT = 120;

    private final static Logger LOG = LoggerFactory.getLogger(S3AwsClient.class);

    private final S3Client s3;
    private final ObjectMapper objectMapper;

    S3AwsClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.s3 = S3Client
                .builder()
                .region(Region.EU_WEST_3)
                .overrideConfiguration(b -> b.apiCallTimeout(Duration.ofSeconds(API_CALL_TIMEOUT))
                        .apiCallAttemptTimeout(Duration.ofSeconds(API_CALL_ATTEMPT_TIMEOUT)))
                .build();
    }

    public Stream<Fund> getFundList(String bucketName, String keyName){
        final GetObjectRequest getRequest = GetObjectRequest.builder().bucket(bucketName).key(keyName).build();
        try (final ResponseInputStream<GetObjectResponse> inputStream = s3.getObject(getRequest,
                ResponseTransformer.toInputStream())){
            final List<FundDto> funds = objectMapper.readValue(inputStream.readAllBytes(), new TypeReference<>() {
            });
            return funds.stream().map(FundDto::toDomain);
        } catch (IOException e) {
            LOG.error(String.format("Fail to get fund list from s3 with bucket name [%s], key name [%s]", bucketName, keyName), e);
            return Stream.empty();
        }
    }

}
