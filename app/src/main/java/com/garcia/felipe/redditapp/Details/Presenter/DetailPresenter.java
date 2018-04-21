package com.garcia.felipe.redditapp.Details.Presenter;

import com.garcia.felipe.redditapp.Models.RedditPost;

public interface DetailPresenter {
    void onCreate(RedditPost item);
    void onDestroy();
}
