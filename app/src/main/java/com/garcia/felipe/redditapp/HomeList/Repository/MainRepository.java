package com.garcia.felipe.redditapp.HomeList.Repository;

public interface MainRepository {

    void getDataFromServer();
    void getDataFromLocalStorage();
    void saveDataToLocalStorage();
}
