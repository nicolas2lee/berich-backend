package tao.berich.getfundlist.infra.service;


import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;
import tao.berich.getfundlist.domain.model.Fund;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FundServiceImplTest {
    private FundServiceImpl fundService;

    @Before
    public void setUp() throws Exception {
        fundService = new FundServiceImpl();
    }

    @Test
    public void should_return_fund_list() {
        StepVerifier.create(fundService.getFunds())
                .expectNextCount(596l)
                .expectComplete()
                .verify();
    }

    @Test
    public void should_return_content_in_bracket() {
        String s = "_t.push({val:\"600168\",val2:\"武汉控股\",val3:\"whkg\"});";

        final String result = fundService.findContentInsideBracket(s);

        assertThat(result).isEqualTo("val:\"600168\",val2:\"武汉控股\",val3:\"whkg\"");
    }

    @Test
    public void should_return_fund_given_correct_string() {
        String s = "val:\"600168\",val2:\"武汉控股\",val3:\"whkg\"";

        final Fund result = fundService.mapToFund(s);

        assertThat(result).isEqualTo(new Fund("600168", "武汉控股", "whkg"));
    }
}