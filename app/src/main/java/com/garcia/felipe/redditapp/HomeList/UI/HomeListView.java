package com.garcia.felipe.redditapp.HomeList.UI;

import com.garcia.felipe.redditapp.Models.RedditPost;

import java.util.ArrayList;

public interface HomeListView {
    void hideSwipeProgressBar();

    void showSwipeProgressBar();

    void showMessage(String string);

    void showProgressDialog(String message);

    void hideProgressDialog();

    void setItemsToListView(ArrayList<RedditPost> items);

    void moveVerticalScrollPosition(int scrollPosition);

    int getVerticalScrollRange();

    void onClickListener(RedditPost object);
}
