package com.launchpad.crypto.coinmarketcap.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Ticker {
    private String id;
    private String name;
    private String rank;
    private String symbol;

    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("market_cap_usd")
    private String marketCapUsd;
    @SerializedName("percent_change_1h")
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    private String percentChange24h;
    @SerializedName("percent_change_7d")
    private String percentChange7d;
    @SerializedName("price_btc")
    private String priceBtc;
    @SerializedName("price_usd")
    private String priceUsd;
    @SerializedName("available_supply")
    private String availableSupply;
    @SerializedName("total_supply")
    private String totalSupply;
    @SerializedName("max_supply")
    private String maxSupply;
    @SerializedName("24h_volume_usd")
    private String volumeLast24Usd;

    public Ticker(final Ticker ticker) {
        this.id = ticker.id;
        this.name = ticker.name;
        this.rank = ticker.rank;
        this.symbol = ticker.symbol;
        this.availableSupply = ticker.availableSupply;
        this.lastUpdated = ticker.lastUpdated;
        this.marketCapUsd = ticker.marketCapUsd;
        this.percentChange1h = ticker.percentChange1h;
        this.percentChange7d = ticker.percentChange7d;
        this.percentChange24h = ticker.percentChange24h;
        this.priceUsd = ticker.priceUsd;
        this.priceBtc = ticker.priceUsd;
        this.totalSupply = ticker.totalSupply;
        this.maxSupply = ticker.maxSupply;
        this.volumeLast24Usd = ticker.volumeLast24Usd;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    @NonNull
    public String getPercentChange1h() {
        if (percentChange1h == null) {
            return String.valueOf(0);
        }
        return percentChange1h;
    }

    @NonNull
    public String getPercentChange24h() {
        if (percentChange24h == null) {
            return String.valueOf(0);
        }
        return percentChange24h;
    }

    @NonNull
    public String getPercentChange7d() {
        if (percentChange7d == null) {
            return String.valueOf(0);
        }
        return percentChange7d;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    @NonNull
    public String getMaxSupply() {
        if (maxSupply == null) {
            return "";
        }
        return maxSupply;
    }

    public String getVolumeLast24Usd() {
        return volumeLast24Usd;
    }
}
