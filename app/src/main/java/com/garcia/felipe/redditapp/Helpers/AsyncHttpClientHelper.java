package com.garcia.felipe.redditapp.Helpers;

import com.loopj.android.http.AsyncHttpClient;

public class AsyncHttpClientHelper {

    private static int TIMEOUT = 30 * 1000;
    private static int MAX_CONN = 5;
    private AsyncHttpClient asyncHttpClient;

    public AsyncHttpClientHelper() {
        this.asyncHttpClient = new AsyncHttpClient(true, 80, 443);
        configure();
    }

    private void configure() {
        asyncHttpClient.setConnectTimeout(TIMEOUT);
        asyncHttpClient.setMaxConnections(MAX_CONN);
    }

    public AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient;
    }
}
