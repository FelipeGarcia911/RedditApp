package com.garcia.felipe.redditapp.Helpers.Image;

import android.widget.ImageView;
import android.widget.ProgressBar;

public interface ImageLoader {
    void load(ImageView imgAvatar, String url);

    void load(ImageView imgAvatar, String url, ProgressBar progressBar);
}
