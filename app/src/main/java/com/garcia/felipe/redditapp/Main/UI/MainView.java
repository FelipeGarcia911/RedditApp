package com.garcia.felipe.redditapp.Main.UI;

import com.garcia.felipe.redditapp.Models.RedditPost;

public interface MainView {

    void navToDetailsFragment(RedditPost item);
    void navToHomeListViewFragment();
    void showMessage(String string);

    void navToAboutFragment();
}
