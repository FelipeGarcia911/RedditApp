package com.garcia.felipe.redditapp.HomeList.UI.Adapters;

import com.garcia.felipe.redditapp.Models.RedditPost;

public interface OnItemClickListener {
    void onClick(RedditPost item);
    void onLongClick(RedditPost item);
}
