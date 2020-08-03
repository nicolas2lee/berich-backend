package tao.berich.microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"day":"2020-03-04 10:30:00","open":"21.520","high":"22.300","low":"21.520","close":"22.180","volume":"1796443"}
 */
public class FundDetailHistory {

    @JsonProperty("day")
    private String datetime;

    @JsonProperty("open")
    private String openPrice;

    @JsonProperty("high")
    private String highPrice;

    @JsonProperty("low")
    private String lowPrice;

    @JsonProperty("close")
    private String closePrice;

    @JsonProperty("volume")
    private String volume;

    public FundDetailHistory() {
    }

    public String getDatetime() {
        return datetime;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
