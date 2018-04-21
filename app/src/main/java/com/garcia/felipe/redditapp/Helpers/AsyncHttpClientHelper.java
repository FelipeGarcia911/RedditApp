package com.garcia.felipe.redditapp.Helpers;

import com.loopj.android.http.AsyncHttpClient;

public class AsyncHttpClientHelper {

    private AsyncHttpClient asyncHttpClient;

    public AsyncHttpClientHelper() {
        this.asyncHttpClient = new AsyncHttpClient(true, 80, 443);
        configure();
    }

    private void configure() {
        int TIMEOUT = 30 * 1000;
        int MAX_CONN = 5;
        asyncHttpClient.setConnectTimeout(TIMEOUT);
        asyncHttpClient.setMaxConnections(MAX_CONN);
    }

    public AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient;
    }
}
