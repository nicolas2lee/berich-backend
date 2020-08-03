package tao.berich.microservice.infra.fund.realtime.sina.client;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tao.berich.microservice.domain.model.FundDetail;
import tao.berich.microservice.infra.fund.HttpClient;
import tao.berich.microservice.infra.fund.dto.FundDetailDto;

import java.io.StringReader;
import java.util.List;

@Component
public class SinaRealTimeClient implements HttpClient {
    private final WebClient webClient;

    public SinaRealTimeClient() {
        this.webClient = HttpClient.buildWebClient("http://hq.sinajs.cn");
    }

    public Mono<FundDetail> getFundDetailByFundCode(String fundCode){
        final String uri = String.format("/list=sh%s", fundCode);
        return webClient.get().uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .map(x-> mapToFundDetail(x, fundCode));
    }

    FundDetail mapToFundDetail(String line, String fundCode) {
        final String csvLine = line.split("=")[1].trim().replaceAll("\"", "");
        final FundDetailDto fundDetailDto = mapCsvToFundDetailDto(csvLine);
        return fundDetailDto.mapToDomain(fundCode);
    }

    FundDetailDto mapCsvToFundDetailDto(String csvLine) {
        ColumnPositionMappingStrategy positionStrategy = new ColumnPositionMappingStrategy();
        String[] columns = new String[]{"chineseName","todayOpenPrice", "yesterdayClosePrice", "currentPrice", "maxPrice", "minPrice", "bidSinglePrice", "askSinglePrice", "hundrudQuantity",
        "bid1Quantity", "bid1Price", "bid2Quantity", "bid2Price", "bid3Quantity", "bid3Price", "bid4Quantity", "bid4Price", "bid5Quantity", "bid5Price",
        "ask1Quantity", "ask1Price", "ask2Quantity", "ask2Price", "ask3Quantity", "ask3Price", "ask4Quantity", "ask4Price", "ask5Quantity", "ask5Price",
        "localDate", "localDateTime", "lastMarketTime"};
        positionStrategy.setColumnMapping(columns);
        positionStrategy.setType(FundDetailDto.class);

        CsvToBean csvToBean = new CsvToBeanBuilder<FundDetailDto>(new StringReader(csvLine))
                .withMappingStrategy(positionStrategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        final List<FundDetailDto> reuslt = csvToBean.parse();
        return reuslt.get(0);
    }
}


