package tao.berich.microservice.domain.model;

import java.math.BigDecimal;

public class FundDetailBuilder {
    private Fund fund;
    private BigDecimal todayOpenPrice;
    private BigDecimal yesterdayClosePrice;
    private BigDecimal currentPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal bidSinglePrice;
    private BigDecimal askSinglePrice;

    public FundDetailBuilder setFund(Fund fund) {
        this.fund = fund;
        return this;
    }

    public FundDetailBuilder setTodayOpenPrice(BigDecimal todayOpenPrice) {
        this.todayOpenPrice = todayOpenPrice;
        return this;
    }

    public FundDetailBuilder setYesterdayClosePrice(BigDecimal yesterdayClosePrice) {
        this.yesterdayClosePrice = yesterdayClosePrice;
        return this;
    }

    public FundDetailBuilder setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public FundDetailBuilder setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public FundDetailBuilder setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public FundDetailBuilder setBidSinglePrice(BigDecimal bidSinglePrice) {
        this.bidSinglePrice = bidSinglePrice;
        return this;
    }

    public FundDetailBuilder setAskSinglePrice(BigDecimal askSinglePrice) {
        this.askSinglePrice = askSinglePrice;
        return this;
    }

    public FundDetail createFundDetail() {
        return new FundDetail(fund, todayOpenPrice, yesterdayClosePrice, currentPrice, maxPrice, minPrice, bidSinglePrice, askSinglePrice);
    }
}