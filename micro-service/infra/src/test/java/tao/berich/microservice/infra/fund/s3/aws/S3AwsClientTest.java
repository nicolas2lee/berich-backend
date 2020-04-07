package tao.berich.microservice.infra.fund.s3.aws;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import tao.berich.microservice.domain.model.Fund;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class S3AwsClientTest {

    private static final String FUND_BUCKET_NAME = "fund-list";
    private static final String FUND_KEY_NAME= "fund.json";

    private S3AwsClient s3;

    @Before
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        s3 = new S3AwsClient(objectMapper);
    }

    @Test
    public void should_return_fund_list_when_read_from_s3() {
        final List<Fund> fundList = s3.getFundList(FUND_BUCKET_NAME, FUND_KEY_NAME).collect(Collectors.toList());

        assertThat(fundList).isNotEmpty();
    }
}