package com.garcia.felipe.redditapp.HomeList.Interactor;

import com.garcia.felipe.redditapp.Helpers.EventBus.GreenRobotEventBus;
import com.garcia.felipe.redditapp.HomeList.Events.DataEvent;
import com.garcia.felipe.redditapp.HomeList.Repository.MainRepositoryImp;
import com.garcia.felipe.redditapp.Models.RedditItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class HomeListInteractorImp implements HomeListInteractor {

    private final GreenRobotEventBus eventBus;
    private final MainRepositoryImp repository;
    private ArrayList<RedditItem> items;

    public HomeListInteractorImp() {
        this.items = new ArrayList<>();
        this.repository = new MainRepositoryImp();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void refreshList() {
        ArrayList<RedditItem> dataFromLocalStorage = repository.getDataFromLocalStorage();
        if (dataFromLocalStorage.size() > 0) {
            onSuccessGetList(dataFromLocalStorage);
        } else {
            repository.getDataFromServer();
        }
    }

    @Subscribe
    public void onGetDataFromServerEvent(DataEvent event) {
        switch (event.getEventType()) {
            case DataEvent.ON_SUCCESS:
                items = event.getList();
                if (items.size() > 0) repository.saveDataToLocalStorage(items);
                onSuccessGetList(items);
                return;
            case DataEvent.ON_FAILURE:
                onFailureGetList(event.getMsgError());
                return;
            default:
                onFailureGetList("Unknown error, please try again.");
        }
    }

    private void onFailureGetList(String message) {
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_FAILURE);
        eventBus.post(dataEvent);
    }

    private void onSuccessGetList(ArrayList<RedditItem> items) {
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(DataEvent.ON_SUCCESS);
        dataEvent.setList(items);
        eventBus.post(dataEvent);
    }


    @Override
    public void onStartListView() {
        ArrayList<RedditItem> dataFromLocalStorage = repository.getDataFromLocalStorage();
        if (dataFromLocalStorage.size() > 0) {
            onSuccessGetList(dataFromLocalStorage);
        } else {
            onFailureGetList("Empty local data.");
        }
    }
}
