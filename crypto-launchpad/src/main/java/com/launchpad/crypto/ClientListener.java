package com.launchpad.crypto;

/**
 * Calls back on the UI thread with the requested data.
 * @param <T> The data type to return when successful.
 */
public interface  ClientListener<T> {
    void onSuccess(final T data);

    void onError(final Exception e);
}
