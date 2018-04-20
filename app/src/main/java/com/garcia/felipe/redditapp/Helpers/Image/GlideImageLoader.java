package com.garcia.felipe.redditapp.Helpers.Image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.garcia.felipe.redditapp.R;

public class GlideImageLoader implements ImageLoader {

    private final Context context;
    private final RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.context = context;
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
        if (url != null && !url.isEmpty()) {
            requestManager.load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .into(imgAvatar);
        }
    }
}
