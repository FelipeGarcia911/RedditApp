package com.garcia.felipe.redditapp.HomeList.Presenter;

import com.garcia.felipe.redditapp.HomeList.Events.ListEvent;
import com.garcia.felipe.redditapp.Models.RedditPost;

import org.greenrobot.eventbus.Subscribe;

public interface HomeListPresenter {

    void onCreate();
    void onDestroy();
    void initListView();

    @Subscribe
    void onEventRefreshList(ListEvent event);

    void onItemClick(RedditPost object);
    void onSwipeTop();
    void onSwipeBottom();
}
