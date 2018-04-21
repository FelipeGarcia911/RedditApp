package com.garcia.felipe.redditapp.HomeList.Repository;

import com.garcia.felipe.redditapp.Models.RedditPost;

import java.util.ArrayList;

interface MainRepository {

    void getDataFromServer();

    ArrayList<RedditPost> getDataFromLocalStorage();

    void saveDataToLocalStorage(ArrayList<RedditPost> items);
}
