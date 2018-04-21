package com.garcia.felipe.redditapp.Main.Presenter;

import com.garcia.felipe.redditapp.Details.Events.DetailEvent;
import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.Main.UI.MainView;
import com.garcia.felipe.redditapp.Models.RedditPost;

import org.greenrobot.eventbus.Subscribe;

public class MainPresenterImp implements MainPresenter {

    private MainView view;
    private final GreenRobotEventBus eventBus;

    public MainPresenterImp(MainView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onItemDetail(DetailEvent event) {
        switch (event.getEventType()) {
            case DetailEvent.ON_DETAIL_REQUEST:
                RedditPost item = event.getItem();
                view.navToDetailsFragment(item);
                return;
            default:
                view.showMessage("Error showing details, please try again.");
        }
    }

    @Override
    public void onNavHomeList() {
        if (view != null){
            view.navToHomeListViewFragment();
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
        view.navToHomeListViewFragment();
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void onNavAbout() {
        view.navToAboutFragment();
    }
}
