package com.launchpad.crypto.coinmarketcap;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.launchpad.crypto.ClientListener;
import com.launchpad.crypto.CryptoLaunchpad;
import com.launchpad.crypto.coinmarketcap.models.Ticker;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CoinMarketCapClient {

    private static final int TICKER_CACHE_TTL_MIN = 5;

    private static final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * Gets the ticker data from CoinMarketCap's API using defaults. This will return
     * the top {@link CoinMarketCapRequestBuilder#DEFAULT_TICKER_LIMIT} currencies. To customize this,
     * use {@link #getTicker(ClientListener, int)}.
     *
     * @param listener called when the data is ready or an error has occurred
     */
    public static void getTicker(final ClientListener<List<Ticker>> listener) {
        getTicker(listener, CoinMarketCapRequestBuilder.DEFAULT_TICKER_LIMIT);
    }

    /**
     * Gets the ticker data from CoinMarketCap's API.
     *
     * @param listener called when the data is ready or an error has occurred
     * @param limit limits the number of items in the response. Example, a limit of 10 will show the top 10 currencies, 1-10.
     */
    public static void getTicker(@Nullable final ClientListener<List<Ticker>> listener, final int limit) {
        if (listener == null) {
            throw new IllegalArgumentException("The listener cannot be null!");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("Specified limit cannot be negative.");
        }
        final String requestString = CoinMarketCapRequestBuilder.create()
                .withLimit(limit)
                .build();

        final Request request = new Request.Builder()
                .cacheControl(
                        new CacheControl.Builder()
                                .maxAge(TICKER_CACHE_TTL_MIN, TimeUnit.MINUTES)
                                .build())
                .url(requestString)
                .build();

        final OkHttpClient client = CryptoLaunchpad.get().getOkHttpClient();
        // Get a handler that can be used to post to the main thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                // Process the data on the worker thread
                final List<Ticker> cryptoMetadataList = deserializeTickerResponse(response.body());

                // post back on UI thread
                handler.post(new Runnable() {
                    public void run() {
                        if (cryptoMetadataList == null || cryptoMetadataList.isEmpty()) {
                            listener.onSuccess(Collections.<Ticker>emptyList());
                        }

                        listener.onSuccess(cryptoMetadataList);
                    }
                });
                response.body().close();
            }

            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    public void run() {
                        listener.onError(e);
                    }
                });
            }
        });
    }

    /**
     * Deserialize the HTTP response into a clean POJO output.
     * @param body response body from the request
     * @return list of data for use
     */
    private static List<Ticker> deserializeTickerResponse(@Nullable final ResponseBody body) {
        if (body == null) {
            return Collections.emptyList();
        }

        final Type listType = new TypeToken<List<Ticker>>() { }.getType();
        final List<Ticker> cryptoMetadataList = CryptoLaunchpad.get().getGson().fromJson(body.charStream(), listType);
        if (cryptoMetadataList == null || cryptoMetadataList.isEmpty()) {
            return Collections.emptyList();
        }

        return cryptoMetadataList;
    }
}
