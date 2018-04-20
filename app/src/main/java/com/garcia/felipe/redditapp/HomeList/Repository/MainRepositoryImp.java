package com.garcia.felipe.redditapp.HomeList.Repository;

import com.garcia.felipe.redditapp.Helpers.AsyncHttpClientHelper;
import com.garcia.felipe.redditapp.Helpers.Constants;
import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.Helpers.HttpConnectionHelper;
import com.garcia.felipe.redditapp.Helpers.LocalStorage.ListLocalStorageHelper;
import com.garcia.felipe.redditapp.HomeList.Events.DataEvent;
import com.garcia.felipe.redditapp.Models.RedditItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainRepositoryImp implements MainRepository {

    private final GreenRobotEventBus eventBus;
    private final AsyncHttpClient asyncHttpClient;
    private final HttpConnectionHelper connectionHelper;


    public MainRepositoryImp() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.connectionHelper = new HttpConnectionHelper();
        this.asyncHttpClient = new AsyncHttpClientHelper().getAsyncHttpClient();
    }

    @Override
    public void getDataFromServer() {
        String urlConnection = Constants.URL_SERVER;
        asyncHttpClient.get(urlConnection, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public ArrayList<RedditItem> getDataFromLocalStorage() {
        ListLocalStorageHelper listLocalStorageHelper = ListLocalStorageHelper.getInstance();
        return listLocalStorageHelper.getList();
    }

    @Override
    public void saveDataToLocalStorage(ArrayList<RedditItem> items) {
        ListLocalStorageHelper listLocalStorageHelper = ListLocalStorageHelper.getInstance();
        listLocalStorageHelper.saveList(items);
    }

    private void onGetDatSuccess(){
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_SUCCESS);
        eventBus.post(dataEvent);
    }

    private void onGetDatFailure(){
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_FAILURE);
        eventBus.post(dataEvent);
    }
}
