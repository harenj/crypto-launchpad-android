package com.launchpad.crypto.coinmarketcap;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/** Request builder for crypto metadata. */
public class CoinMarketCapRequestBuilder {

    private static final String ATTRIBUTE_CONVERT = "convert";
    private static final String ATTRIBUTE_LIMIT = "limit";
    private static final String ATTRIBUTE_START = "start";
    private static final String BASE_URL = "https://api.coinmarketcap.com/v1/ticker/";

    private int limit = -1;
    private int startIndex;
    private String currencyType;

    static final int DEFAULT_TICKER_LIMIT = 100;

    /**
     * Creates a {@link CoinMarketCapRequestBuilder}.
     *
     * @return {@link CoinMarketCapRequestBuilder}
     */
    @NonNull
    public static CoinMarketCapRequestBuilder create() {
        return new CoinMarketCapRequestBuilder();
    }

    /** Private constructor. */
    private CoinMarketCapRequestBuilder() { }

    /**
     * Sets the return limit of the request. A limit of 0 will return all coins.
     *
     * @param limit limit to set
     * @return current instance
     */
    @NonNull
    public CoinMarketCapRequestBuilder withLimit(final int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Sets the start index of the request.
     *
     * @param startIndex index to start at
     * @return current instance
     */
    @NonNull
    public CoinMarketCapRequestBuilder withStartIndex(final int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    /**
     * Sets the currency type of the request.
     *
     * @param currencyType currency type to retrieve
     * @return current instance
     */
    @NonNull
    public CoinMarketCapRequestBuilder withCurrencyType(@NonNull final String currencyType) {
        this.currencyType = currencyType;
        return this;
    }

    /**
     * Builds the request string.
     *
     * @return request string with attributes
     */
    @NonNull
    public String build() {
        final StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        if (!TextUtils.isEmpty(currencyType)) {
            appendValue(stringBuilder, ATTRIBUTE_CONVERT, currencyType);
        }
        if (startIndex > 0) {
            appendValue(stringBuilder, ATTRIBUTE_START, String.valueOf(startIndex));
        }
        if (limit >= 0) {
            appendValue(stringBuilder, ATTRIBUTE_LIMIT, String.valueOf(limit));
        } else {
            appendValue(stringBuilder, ATTRIBUTE_LIMIT, String.valueOf(DEFAULT_TICKER_LIMIT));
        }
        return stringBuilder.toString();
    }

    /**
     * Appends a value to the request string.
     *
     * @param stringBuilder string to build upon
     * @param keyToAppend   key to append to string
     * @param valueToAppend value to append to key
     */
    private void appendValue(final StringBuilder stringBuilder, final String keyToAppend, final String valueToAppend) {
        if (stringBuilder.indexOf("?") < 0) {
            stringBuilder.append("?");
        } else {
            stringBuilder.append("&");
        }
        stringBuilder.append(keyToAppend).append("=").append(valueToAppend);
    }
}
