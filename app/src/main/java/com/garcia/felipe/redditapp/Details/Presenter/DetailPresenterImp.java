package com.garcia.felipe.redditapp.Details.Presenter;

import com.garcia.felipe.redditapp.Details.UI.DetailFragment;
import com.garcia.felipe.redditapp.Models.RedditPost;

public class DetailPresenterImp implements DetailPresenter {


    private final DetailFragment view;

    public DetailPresenterImp(DetailFragment detailFragment) {
        this.view = detailFragment;
    }

    @Override
    public void onCreate(RedditPost item) {
        if (item != null){
            view.setTitle(item.getTitle());
            view.setDate(item.getDate());
            view.setCategory(item.getCategory());
            view.setImage(item.getBannerImageURL());
            view.setDescription(item.getLongDescription());
        }
    }

    @Override
    public void onDestroy() {

    }
}
