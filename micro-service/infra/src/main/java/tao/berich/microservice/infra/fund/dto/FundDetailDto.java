package tao.berich.microservice.infra.fund.dto;

import tao.berich.microservice.domain.model.Fund;
import tao.berich.microservice.domain.model.FundDetail;
import tao.berich.microservice.domain.model.FundDetailBuilder;

import java.math.BigDecimal;

/**
 * temp[0]------宝光股份------股票名称
 * temp[1]------18.100------今日开盘价
 * temp[2]------18.190------昨日收盘价
 * temp[3]------19.080------现价（股票当前价，收盘以后这个价格就是当日收盘价）
 * temp[4]------19.090------最高价
 * temp[5]------18.100------最低价
 * temp[6]------19.080------买一
 * temp[7]------19.090------卖一
 * temp[8]------2871283------总量（成交量，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；）
 * temp[9]------53939719.000------成交额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
 * temp[10]------1100------买一挂单数量（也就是11手）
 * temp[11]------19.080------买一
 * temp[12]------4600------买二挂单数量
 * temp[13]------19.070------买二
 * temp[14]------5500------买三挂单数量
 * temp[15]------19.060------买三
 * temp[16]------6200------买四挂单数量
 * temp[17]------19.050------买四
 * temp[18]------13900------买五挂单数量
 * temp[19]------19.040------买五
 * temp[20]------13159------卖一挂单数量
 * temp[21]------19.090------卖一
 * temp[22]------20122------卖二挂单数量
 * temp[23]------19.100------卖二
 * temp[24]------8000------卖三数量
 * temp[25]------19.110------卖三
 * temp[26]------7800------卖四数量
 * temp[27]------19.120------卖四
 * temp[28]------1300------卖五数量
 * temp[29]------19.130------卖五
 * temp[30]------2016-06-22------日期
 * temp[31]------15:00:00------时间
 * temp[32]------00------00表示收盘
 *
 * ————————————————
 * 原文链接：https://blog.csdn.net/zai_yuzhong/java/article/details/51735769
 */
public class FundDetailDto {
    private String chineseName;
    private BigDecimal todayOpenPrice;
    private BigDecimal yesterdayClosePrice;
    private BigDecimal currentPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal bidSinglePrice;
    private BigDecimal askSinglePrice;
    private BigDecimal hundrudQuantity;

    private BigDecimal bid1Quantity;
    private BigDecimal bid1Price;

    private BigDecimal bid2Quantity;
    private BigDecimal bid2Price;

    private BigDecimal bid3Quantity;
    private BigDecimal bid3Price;

    private BigDecimal bid4Quantity;
    private BigDecimal bid4Price;

    private BigDecimal bid5Quantity;
    private BigDecimal bid5Price;

    private BigDecimal ask1Quantity;
    private BigDecimal ask1Price;

    private BigDecimal ask2Quantity;
    private BigDecimal ask2Price;

    private BigDecimal ask3Quantity;
    private BigDecimal ask3Price;

    private BigDecimal ask4Quantity;
    private BigDecimal ask4Price;

    private BigDecimal ask5Quantity;
    private BigDecimal ask5Price;

    private String localDate;
    private String localDateTime;

    private String lastMarketTime;

    public String getChineseName() {
        return chineseName;
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

    public BigDecimal getHundrudQuantity() {
        return hundrudQuantity;
    }

    public BigDecimal getBid1Quantity() {
        return bid1Quantity;
    }

    public BigDecimal getBid1Price() {
        return bid1Price;
    }

    public BigDecimal getBid2Quantity() {
        return bid2Quantity;
    }

    public BigDecimal getBid2Price() {
        return bid2Price;
    }

    public BigDecimal getBid3Quantity() {
        return bid3Quantity;
    }

    public BigDecimal getBid3Price() {
        return bid3Price;
    }

    public BigDecimal getBid4Quantity() {
        return bid4Quantity;
    }

    public BigDecimal getBid4Price() {
        return bid4Price;
    }

    public BigDecimal getBid5Quantity() {
        return bid5Quantity;
    }

    public BigDecimal getBid5Price() {
        return bid5Price;
    }

    public BigDecimal getAsk1Quantity() {
        return ask1Quantity;
    }

    public BigDecimal getAsk1Price() {
        return ask1Price;
    }

    public BigDecimal getAsk2Quantity() {
        return ask2Quantity;
    }

    public BigDecimal getAsk2Price() {
        return ask2Price;
    }

    public BigDecimal getAsk3Quantity() {
        return ask3Quantity;
    }

    public BigDecimal getAsk3Price() {
        return ask3Price;
    }

    public BigDecimal getAsk4Quantity() {
        return ask4Quantity;
    }

    public BigDecimal getAsk4Price() {
        return ask4Price;
    }

    public BigDecimal getAsk5Quantity() {
        return ask5Quantity;
    }

    public BigDecimal getAsk5Price() {
        return ask5Price;
    }

    public String getLocalDate() {
        return localDate;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public String getLastMarketTime() {
        return lastMarketTime;
    }

    public FundDetail mapToDomain(String fundCode){
        return new FundDetailBuilder()
                .setFund(new Fund(fundCode, chineseName, ""))
                .setCurrentPrice(currentPrice)
                .setMaxPrice(maxPrice)
                .setMinPrice(minPrice)
                .setAskSinglePrice(askSinglePrice)
                .setBidSinglePrice(bidSinglePrice)
                .createFundDetail();
    }
}
