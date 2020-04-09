package tao.berich.microservice.infra.fund.realtime.sina.client;


import org.junit.Before;
import org.junit.Test;
import tao.berich.microservice.infra.fund.dto.FundDetailDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SinaClientTest {
    private SinaClient sinaClient;

    @Before
    public void setUp() throws Exception {
        sinaClient = new SinaClient();
    }

    @Test
    public void should_return_fund_detail_when_given_sinajs_response() {
        //String line = "var hq_str_sh600379=\"宝光股份,18.100,18.190,19.080,19.090,18.100,19.080,19.090,2871283,53939719.000,1100,19.080,4600,19.070,5500,19.060,6200,19.050,13900,19.040,13159,19.090,20122,19.100,8000,19.110,7800,19.120,1300,19.130,2016-06-22,15:00:00,00\";\n";
        String line = "宝光股份,18.100,18.190,19.080,19.090,18.100,19.080,19.090,2871283,53939719.000,1100,19.080,4600,19.070,5500,19.060,6200,19.050,13900,19.040,13159,19.090,20122,19.100,8000,19.110,7800,19.120,1300,19.130,2016-06-22,15:00:00,00";

        final FundDetailDto result = sinaClient.mapCsvToFundDetailDto(line);

        assertThat(result.getCurrentPrice()).isEqualTo(new BigDecimal("19.080"));
    }
}