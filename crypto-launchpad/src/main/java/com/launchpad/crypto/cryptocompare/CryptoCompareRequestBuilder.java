package com.launchpad.crypto.cryptocompare;

import android.support.annotation.NonNull;

public class CryptoCompareRequestBuilder {
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/all/coinlist";

    /**
     * Creates a {@link CryptoCompareRequestBuilder}.
     *
     * @return {@link CryptoCompareRequestBuilder}
     */
    @NonNull
    public static CryptoCompareRequestBuilder create() {
        return new CryptoCompareRequestBuilder();
    }

    /**
     * Private constructor.
     */
    private CryptoCompareRequestBuilder() {
    }

    /**
     * Builds the request string.
     *
     * @return request string with attributes
     */
    @NonNull
    public String build() {
        return BASE_URL;
    }
}
