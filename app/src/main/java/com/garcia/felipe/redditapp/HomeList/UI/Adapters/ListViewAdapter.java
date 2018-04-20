package com.garcia.felipe.redditapp.HomeList.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.garcia.felipe.redditapp.Helpers.Image.ImageLoader;
import com.garcia.felipe.redditapp.HomeList.UI.HomeListFragment;
import com.garcia.felipe.redditapp.Models.RedditItem;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter implements ListViewAdapterView{

    public ListViewAdapter(ArrayList<RedditItem> items, ImageLoader loader, HomeListFragment fragment) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void add(RedditItem object) {

    }

    @Override
    public void update(RedditItem object) {

    }

    @Override
    public void remove(RedditItem object) {

    }
}
