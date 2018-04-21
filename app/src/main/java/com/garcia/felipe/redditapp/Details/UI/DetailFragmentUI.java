package com.garcia.felipe.redditapp.Details.UI;

import com.garcia.felipe.redditapp.Models.RedditPost;

interface DetailFragmentUI {
    void setRedditPost(RedditPost redditPost);

    void setTitle(String title);
    void setDescription(String description);
    void setImage(String urlImage);

    void setDate(String string);

    void setCategory(String category);
}
