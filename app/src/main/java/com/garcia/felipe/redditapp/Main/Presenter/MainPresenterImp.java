package com.garcia.felipe.redditapp.Main.Presenter;

import com.garcia.felipe.redditapp.Main.UI.MainActivity;
import com.garcia.felipe.redditapp.Main.UI.MainView;

public class MainPresenterImp implements MainPresenter {

    private final MainView view;

    public MainPresenterImp(MainView view) {
        this.view = view;
    }

    @Override
    public void onMainItemClick() {
        if (view != null){
            view.navToHomeListViewFragment();
        }
    }
}
