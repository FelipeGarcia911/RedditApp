package com.garcia.felipe.redditapp.HomeList.UI.Adapters;

import com.garcia.felipe.redditapp.Models.RedditItem;

public interface ListViewAdapterView {
    void add(RedditItem object);
    void update(RedditItem object);
    void remove(RedditItem object);
}
