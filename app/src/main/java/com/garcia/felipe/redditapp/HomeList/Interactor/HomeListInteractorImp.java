package com.garcia.felipe.redditapp.HomeList.Interactor;

import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.HomeList.Events.DataEvent;
import com.garcia.felipe.redditapp.HomeList.Events.ListEvent;
import com.garcia.felipe.redditapp.HomeList.Repository.MainRepositoryImp;
import com.garcia.felipe.redditapp.Models.RedditPost;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class HomeListInteractorImp implements HomeListInteractor {

    private final GreenRobotEventBus eventBus;
    private final MainRepositoryImp repository;
    private ArrayList<RedditPost> redditPosts;

    public HomeListInteractorImp() {
        this.redditPosts = new ArrayList<>();
        this.repository = new MainRepositoryImp();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void refreshList() {
        repository.getDataFromServer();
    }

    @Subscribe
    public void onGetDataFromServerEvent(DataEvent event) {
        switch (event.getEventType()) {
            case DataEvent.ON_SUCCESS:
                redditPosts = event.getList();
                if (redditPosts.size() > 0) repository.saveDataToLocalStorage(redditPosts);
                onSuccessGetList(redditPosts);
                return;
            case DataEvent.ON_FAILURE:
                onFailureGetList(event.getMsgError());
                return;
            default:
                onFailureGetList("Unknown error, please try again.");
        }
    }

    private void onFailureGetList(String message) {
        ListEvent event = new ListEvent();
        event.setEventType(DataEvent.ON_FAILURE);
        event.setMsgError(message);
        eventBus.post(event);
    }

    private void onSuccessGetList(ArrayList<RedditPost> items) {
        ListEvent event = new ListEvent();
        event.setEventType(DataEvent.ON_SUCCESS);
        event.setList(items);
        eventBus.post(event);
    }


    @Override
    public void onStartListView() {
        ArrayList<RedditPost> dataFromLocalStorage = repository.getDataFromLocalStorage();
        if (dataFromLocalStorage.size() > 0) {
            onSuccessGetList(dataFromLocalStorage);
        } else {
            onFailureGetList("Empty local data, please pull to download the new posts.");
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }
}
