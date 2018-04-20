package com.garcia.felipe.redditapp.HomeList.Presenter;

import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.HomeList.Events.ListEvent;
import com.garcia.felipe.redditapp.HomeList.Interactor.HomeListInteractorImp;
import com.garcia.felipe.redditapp.HomeList.UI.HomeListView;
import com.garcia.felipe.redditapp.Models.RedditItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class HomeListPresenterImp implements HomeListPresenter {

    private HomeListView view;
    private final GreenRobotEventBus eventBus;
    private final HomeListInteractorImp interactor;

    public HomeListPresenterImp(HomeListView view) {
        this.view = view;
        this.interactor = new HomeListInteractorImp();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    @Subscribe
    public void onEventRefreshList(ListEvent event) {
        switch (event.getEventType()) {
            case ListEvent.ON_SUCCESS:
                onSuccessRefreshList(event.getList());
                return;
            case ListEvent.ON_FAILURE:
                onFailureRefreshList(event.getMsgError());
                return;
            default:
                onFailureRefreshList("Unknown error, please try again.");
        }
    }

    private void onFailureRefreshList(String msgError) {
        if (view != null) {
            view.hideSwipeProgressBar();
            view.showMessage(msgError);
        }
    }

    private void onSuccessRefreshList(ArrayList<RedditItem> items) {
        if (view != null) {
            view.hideSwipeProgressBar();
            view.setItemsToListView(items);
        }
    }

    private void moveScrollPosition() {
        if (view != null) {
            int scrollOffset = view.getVerticalScrollRange() / 2;
            view.moveVerticalScrollPosition(scrollOffset);
        }
    }

    //----------------------------------------------------------------------------------------------


    @Override
    public void onComplaintClick(RedditItem object) {

    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onSwipeTop() {
        if (view != null) {
            view.showSwipeProgressBar();
        }
        interactor.refreshList();
    }

    @Override
    public void onSwipeBottom() {
        if (view != null) {
            view.showMessage("Bottom reached!.");
        }
    }

    //----------------------------------------------------------------------------------------------


    @Override
    public void initListView() {
        interactor.onStartListView();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

}
