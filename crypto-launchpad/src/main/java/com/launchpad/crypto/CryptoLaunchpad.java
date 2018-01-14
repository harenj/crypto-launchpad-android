package com.launchpad.crypto;

import android.support.annotation.RestrictTo;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

import static android.support.annotation.RestrictTo.Scope.LIBRARY;

public class CryptoLaunchpad {

    private static volatile CryptoLaunchpad singleton = null;
    private Gson gson;
    private OkHttpClient okHttpClient;

    CryptoLaunchpad(final Gson gson, final OkHttpClient httpClient) {
        this.gson = gson;
        this.okHttpClient = httpClient;
    }

    public static CryptoLaunchpad get() {
        if (singleton == null) {
            singleton = new Builder().build();
        }
        return singleton;
    }

    private static class Builder {
        private Gson gson;
        private OkHttpClient okHttpClient;

        public Builder() {
        }

        public CryptoLaunchpad build() {
            gson = new Gson();
            okHttpClient = new OkHttpClient();

            return new CryptoLaunchpad(gson, okHttpClient);
        }
    }

    @RestrictTo(LIBRARY)
    public Gson getGson() {
        return gson;
    }

    @RestrictTo(LIBRARY)
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
