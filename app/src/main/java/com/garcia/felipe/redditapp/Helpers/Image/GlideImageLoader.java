package com.garcia.felipe.redditapp.Helpers.Image;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.garcia.felipe.redditapp.R;

public class GlideImageLoader implements ImageLoader {

    private final RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(final ImageView imageView, String url, final ProgressBar progressBar) {
        if (url != null && !url.isEmpty()) {
            requestManager
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            onFailure(progressBar, imageView);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(ProgressBar.GONE);
                            return false;
                        }
                    }).into(imageView);
        } else {
            onFailure(progressBar, imageView);
        }
    }

    private void onFailure(ProgressBar progressBar, ImageView imageView) {
        progressBar.setVisibility(ProgressBar.GONE);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public void load(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            requestManager.load(url).into(imageView);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}
