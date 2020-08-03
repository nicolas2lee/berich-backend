package tao.berich.microservice.infra.fund.history.sina.client;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import tao.berich.microservice.domain.model.FundDetailHistory;


public class SinaHistoryClientTest {

    private SinaHistoryClient sinaHistoryClient;

    @Before
    public void setUp() throws Exception {
        sinaHistoryClient = new SinaHistoryClient();
    }

    @Test
    @Ignore
    public void should_return_fund_history_when_call_sina_history_endpoint() {
        Flux<FundDetailHistory> result = sinaHistoryClient.getFundDetailByFundCode("000001");

        StepVerifier.create(result)
                .expectSubscription()
                .expectNextCount(412)
                //.expectNextCount(1024)
                .expectComplete()
                .verify();
    }
}