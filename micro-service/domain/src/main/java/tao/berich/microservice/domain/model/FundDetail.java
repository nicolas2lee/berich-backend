package tao.berich.microservice.domain.model;

import java.math.BigDecimal;

public class FundDetail{
    private Fund fund;
    private BigDecimal todayOpenPrice;
    private BigDecimal yesterdayClosePrice;
    private BigDecimal currentPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal bidSinglePrice;
    private BigDecimal askSinglePrice;

    FundDetail(Fund fund, BigDecimal todayOpenPrice, BigDecimal yesterdayClosePrice, BigDecimal currentPrice, BigDecimal maxPrice, BigDecimal minPrice, BigDecimal bidSinglePrice, BigDecimal askSinglePrice) {
        this.fund = fund;
        this.todayOpenPrice = todayOpenPrice;
        this.yesterdayClosePrice = yesterdayClosePrice;
        this.currentPrice = currentPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.bidSinglePrice = bidSinglePrice;
        this.askSinglePrice = askSinglePrice;
    }

    public Fund getFund() {
        return fund;
    }

    public BigDecimal getTodayOpenPrice() {
        return todayOpenPrice;
    }

    public BigDecimal getYesterdayClosePrice() {
        return yesterdayClosePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getBidSinglePrice() {
        return bidSinglePrice;
    }

    public BigDecimal getAskSinglePrice() {
        return askSinglePrice;
    }
}
