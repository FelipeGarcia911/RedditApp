package com.garcia.felipe.redditapp.HomeList.Repository;

import com.garcia.felipe.redditapp.Models.RedditItem;

import java.util.ArrayList;

public interface MainRepository {

    void getDataFromServer();

    ArrayList<RedditItem> getDataFromLocalStorage();

    void saveDataToLocalStorage(ArrayList<RedditItem> items);
}
