package com.garcia.felipe.redditapp.Details.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.garcia.felipe.redditapp.Details.Presenter.DetailPresenter;
import com.garcia.felipe.redditapp.Details.Presenter.DetailPresenterImp;
import com.garcia.felipe.redditapp.Models.RedditPost;
import com.garcia.felipe.redditapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment implements DetailFragmentUI{

    private RedditPost redditPost;
    private DetailPresenter detailPresenter;

    @BindView(R.id.titleDetails) TextView titleDetails;
    @BindView(R.id.categoryDetails)
    TextView categoryDetails;
    @BindView(R.id.dateDetails)
    TextView dateDetails;
    @BindView(R.id.descriptionDetails) TextView descriptionDetails;
    @BindView(R.id.bannerImage) ImageView bannerImage;

    public DetailFragment() {
        this.detailPresenter = new DetailPresenterImp(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        detailPresenter.onCreate(redditPost);
    }

    @Override
    public void setRedditPost(RedditPost redditPost){
        this.redditPost = redditPost;
    }

    @Override
    public void setTitle(String title) {
        titleDetails.setText(Html.fromHtml(title));
    }

    @Override
    public void setDescription(String description) {
        if (description != null){
            descriptionDetails.setText(Html.fromHtml(description));
            descriptionDetails.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override
    public void setDate(String string) {
        dateDetails.setText(string);
    }

    @Override
    public void setCategory(String string) {
        categoryDetails.setText(string);
    }

    @Override
    public void setImage(String urlImage) {
        Glide.with(this).load(urlImage).into(bannerImage);
    }

}
