package com.garcia.felipe.redditapp.HomeList.Repository;

import android.util.Log;

import com.garcia.felipe.redditapp.Helpers.AsyncHttpClientHelper;
import com.garcia.felipe.redditapp.Helpers.Constants;
import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.Helpers.HttpConnectionHelper;
import com.garcia.felipe.redditapp.Helpers.LocalStorage.ListLocalStorageHelper;
import com.garcia.felipe.redditapp.HomeList.Events.DataEvent;
import com.garcia.felipe.redditapp.Models.RedditPost;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                JSONObject jsonObject = connectionHelper.stringBuilderJSONObject(responseBody);
                handleOnGetDataFromServerSuccess(jsonObject);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("HTTP-Error", error.toString());
                onGetDatFailure("Error connecting to the server, please try again later.");
            }

            private void handleOnGetDataFromServerSuccess(JSONObject jsonObject) {
                try {
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray children = data.getJSONArray("children");
                    ArrayList<RedditPost> redditPosts = getRedditObjectFromJSONArray(children);
                    onGetDatSuccess(redditPosts);
                }catch (JSONException e){
                    Log.e("JSON-Error", e.toString());
                    onGetDatFailure("Error parsing JSON data, please try again.");
                }
            }

            private ArrayList<RedditPost> getRedditObjectFromJSONArray(JSONArray jsonArray) throws JSONException{
                ArrayList<RedditPost> arrayList = new ArrayList<>();
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    arrayList.add(createItemFromJSONObject(jsonObject));
                }
                return arrayList;
            }

            private RedditPost createItemFromJSONObject(JSONObject redditPost) throws JSONException{
                JSONObject data = redditPost.getJSONObject("data");

                String title = data.getString("title");
                String shortDescription = data.getString("public_description");
                String longDescription = data.getString("description");
                String iconImageURL = data.getString("icon_img");
                String bannerImageURL = data.getString("banner_img");
                String date = data.getString("created_utc");

                RedditPost redditItem = new RedditPost(title, shortDescription, iconImageURL);
                redditItem.setLongDescription(longDescription);
                redditItem.setBannerImageURL(bannerImageURL);
                redditItem.setDateFromStringUTC(date);
                return redditItem;
            }
        });
    }


    @Override
    public ArrayList<RedditPost> getDataFromLocalStorage() {
        ListLocalStorageHelper listLocalStorageHelper = ListLocalStorageHelper.getInstance();
        return listLocalStorageHelper.getList();
    }

    @Override
    public void saveDataToLocalStorage(ArrayList<RedditPost> items) {
        ListLocalStorageHelper listLocalStorageHelper = ListLocalStorageHelper.getInstance();
        listLocalStorageHelper.saveList(items);
    }

    private void onGetDatSuccess(ArrayList<RedditPost> arrayList){
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_SUCCESS);
        dataEvent.setList(arrayList);
        eventBus.post(dataEvent);
    }

    private void onGetDatFailure(String message){
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_FAILURE);
        dataEvent.setMsgError(message);
        eventBus.post(dataEvent);
    }
}
