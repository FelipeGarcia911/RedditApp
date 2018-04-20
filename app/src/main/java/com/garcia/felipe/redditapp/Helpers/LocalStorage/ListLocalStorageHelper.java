package com.garcia.felipe.redditapp.Helpers.LocalStorage;

import com.garcia.felipe.redditapp.Models.RedditItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListLocalStorageHelper {

    private static final String LIST_KEY = "LIST_KEY";
    private static final String LIST_TIME = "LIST_TIME";

    private Gson gson;
    private SharedPreferencesHelper preferencesHelper;

    public static ListLocalStorageHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize() {
        this.gson = new Gson();
        this.preferencesHelper = SharedPreferencesHelper.getInstance();
    }

    public ArrayList<RedditItem> getList() {
        ArrayList<RedditItem> list = new ArrayList<>();
        String jsonString = preferencesHelper.read(LIST_KEY);
        if (jsonString == null || jsonString.isEmpty()) {
            return list;
        } else {
            Type collectionType = new TypeToken<List<RedditItem>>() {
            }.getType();
            list = gson.fromJson(jsonString, collectionType);
            return list;
        }
    }

    public void saveList(ArrayList<RedditItem> items) {
        String gsonString = gson.toJson(items);
        preferencesHelper.write(LIST_KEY, gsonString);
    }

    public void deleteList() {
        preferencesHelper.remove(LIST_KEY);
        preferencesHelper.remove(LIST_TIME);
    }


    private static class SingletonHolder {
        private static final ListLocalStorageHelper INSTANCE = new ListLocalStorageHelper();
    }
}
