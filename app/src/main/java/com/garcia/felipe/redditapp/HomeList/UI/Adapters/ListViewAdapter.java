package com.garcia.felipe.redditapp.HomeList.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garcia.felipe.redditapp.Helpers.Image.ImageLoader;
import com.garcia.felipe.redditapp.Models.RedditPost;
import com.garcia.felipe.redditapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends RecyclerView.Adapter {

    private final ImageLoader imageLoader;
    private final List<RedditPost> redditPosts;
    private final OnItemClickListener onItemClickListener;

    public ListViewAdapter(ArrayList<RedditPost> redditPosts, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.imageLoader = imageLoader;
        this.redditPosts = redditPosts;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reddit_item_detail, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        RedditPost redditPost = redditPosts.get(position);
        itemViewHolder.setItemData(redditPost, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements ListViewAdapterView {

        private final View view;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.shortDescription) TextView shortDescription;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.thumbnailImage) CircleImageView thumbnailImage;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        ItemViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        private void setClickListener(final RedditPost item, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(item);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(item);
                    return false;
                }
            });
        }

        @Override
        public void setTitle(String string) {
            title.setText(string);
        }

        @Override
        public void setDescription(String string) {
            shortDescription.setText(string);
        }

        @Override
        public void setImage(String string) {
            imageLoader.load(thumbnailImage, string, progressBar);
        }

        @Override
        public void setCategory(String string) {
            category.setText(string);
        }

        void setItemData(RedditPost redditPost, OnItemClickListener onItemClickListener) {
//            setTitle(redditPost.getTitle());
//            setCategory(redditPost.getCategory());
//            setDescription(redditPost.getShortDescription());
//            setImage(redditPost.getIconImageURL());
//            setClickListener(redditPost, onItemClickListener);
        }
    }
}
