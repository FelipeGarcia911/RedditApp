package com.garcia.felipe.redditapp.HomeList.UI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garcia.felipe.redditapp.Helpers.Image.GlideImageLoader;
import com.garcia.felipe.redditapp.Helpers.Image.ImageLoader;
import com.garcia.felipe.redditapp.Helpers.SimpleProgressDialogHelper;
import com.garcia.felipe.redditapp.HomeList.Presenter.HomeListPresenterImp;
import com.garcia.felipe.redditapp.HomeList.UI.Adapters.ListViewAdapter;
import com.garcia.felipe.redditapp.HomeList.UI.Adapters.OnItemClickListener;
import com.garcia.felipe.redditapp.Models.RedditPost;
import com.garcia.felipe.redditapp.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeListFragment extends Fragment implements HomeListView, OnItemClickListener {

    private final HomeListPresenterImp presenter;
    @BindView(R.id.swipe_refresh_layout) SwipyRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ListViewAdapter listAdapter;
    private ImageLoader imageLoader;
    private SimpleProgressDialogHelper progressDialogHelper;

    public HomeListFragment() {
        this.presenter = new HomeListPresenterImp(this);
        presenter.onCreate();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initializeUI();
    }

    private void initializeUI() {
        initDialogs();
        initImageLoader();
        initSwipeRefresh();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        listAdapter = new ListViewAdapter(new ArrayList<RedditPost>(), imageLoader, this);
        recyclerView.setAdapter(listAdapter);

        presenter.initListView();
    }

    private void initImageLoader() {
        imageLoader = new GlideImageLoader(getActivity());
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    presenter.onSwipeTop();
                } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
                    presenter.onSwipeBottom();
                }
            }
        });
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary));
    }

    private void initDialogs() {
        progressDialogHelper = new SimpleProgressDialogHelper(getActivity());
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void hideSwipeProgressBar() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showSwipeProgressBar() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void showMessage(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog(String message) {
        if (progressDialogHelper != null) {
            progressDialogHelper.setMessage(message);
            progressDialogHelper.showProgressDialog();
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void hideProgressDialog() {
        if (progressDialogHelper != null) {
            progressDialogHelper.hideProgressDialog();
        }
    }

    @Override
    public void setItemsToListView(ArrayList<RedditPost> items) {
        listAdapter = new ListViewAdapter(items, imageLoader, this);
        if (recyclerView != null) {
            recyclerView.setAdapter(listAdapter);
        }
    }

    //----------------------------------------------------------------------------------------------


    @Override
    public void moveVerticalScrollPosition(int scrollPosition) {
        if (recyclerView != null) {
            recyclerView.smoothScrollBy(0, scrollPosition);
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public int getVerticalScrollRange() {
        if (recyclerView != null) {
            return recyclerView.computeVerticalScrollExtent();
        } else {
            return 0;
        }
    }

    @Override
    public void onClickListener(RedditPost object) {
        presenter.onItemClick(object);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(RedditPost item) {
        presenter.onItemClick(item);
    }

    @Override
    public void onLongClick(RedditPost item) {
        presenter.onItemClick(item);
    }
}
