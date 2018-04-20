package com.garcia.felipe.redditapp.HomeList.UI;

import com.garcia.felipe.redditapp.Models.RedditItem;

import java.util.ArrayList;

public interface HomeListView {
    void hideSwipeProgressBar();

    void showSwipeProgressBar();

    void showMessage(String string);

    void showProgressDialog(String message);

    void hideProgressDialog();

    void setItemsToListView(ArrayList<RedditItem> items);

    void addItemToListView(RedditItem object);

    void updateItemToListView(RedditItem object);

    void removeComplaintItemToListView(RedditItem object);

    void moveVerticalScrollPosition(int scrollPosition);

    int getVerticalScrollRange();

    void onClickListener(RedditItem object);
}
