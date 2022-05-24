package com.example.cryptoWebsite.trading;

public class TradingRequest {
    private String type;
    private Double value;
    private String coinName;
    private Double currentPrice;

    public TradingRequest() {
    }

    public TradingRequest(String type, String coinName, Double value, Double currentPrice) {
        this.type = type;
        this.value = value;
        this.coinName = coinName;
        this.currentPrice = currentPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
