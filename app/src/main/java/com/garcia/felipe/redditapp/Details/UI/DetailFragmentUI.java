package com.garcia.felipe.redditapp.Details.UI;

import android.graphics.Bitmap;

import com.garcia.felipe.redditapp.Models.RedditPost;

public interface DetailFragmentUI {
    void setRedditPost(RedditPost redditPost);

    void setTitle(String title);
    void setDescription(String description);
    void setImage(String urlImage);
    void setCategory(String category);
}
