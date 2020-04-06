package tao.berich.microservice.infra.fund.s3.aws;


import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;

@Component
public class S3AwsClient {

    private final S3Client s3;
    private static final long API_CALL_TIMEOUT = 60;
    private static final long API_CALL_ATTEMPT_TIMEOUT = 120;

    S3AwsClient() {
        this.s3 = S3Client
                .builder()
                .region(Region.EU_WEST_3)
                .overrideConfiguration(b -> b.apiCallTimeout(Duration.ofSeconds(API_CALL_TIMEOUT))
                        .apiCallAttemptTimeout(Duration.ofSeconds(API_CALL_ATTEMPT_TIMEOUT)))
                .build();
    }

    public void getFundList(String bucketName, String keyName){
        final GetObjectRequest getRequest = GetObjectRequest.builder().bucket(bucketName).key(keyName).build();
        try (final ResponseInputStream<GetObjectResponse> inputStream = s3.getObject(getRequest,
                ResponseTransformer.toInputStream())){

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
