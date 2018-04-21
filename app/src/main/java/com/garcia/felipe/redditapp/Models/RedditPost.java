package com.garcia.felipe.redditapp.Models;

import java.util.Date;

public class RedditPost {

    private Date date;
    private String title;
    private String shortDescription;
    private String longDescription;
    private String iconImageURL;
    private String bannerImageURL;

    public RedditPost(String title, String shortDescription, String imageURL) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.iconImageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIconImageURL() {
        return iconImageURL;
    }

    public void setIconImageURL(String iconImageURL) {
        this.iconImageURL = iconImageURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setDateFromStringUTC(String utc_time){
        
    }

    public String getBannerImageURL() {
        return bannerImageURL;
    }

    public void setBannerImageURL(String bannerImageURL) {
        this.bannerImageURL = bannerImageURL;
    }
}
