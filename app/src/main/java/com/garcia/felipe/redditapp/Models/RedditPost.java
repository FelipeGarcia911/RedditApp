package com.garcia.felipe.redditapp.Models;

import android.text.Html;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class RedditPost {

    private String date;
    private String title;
    private String shortDescription;
    private String longDescription;
    private String iconImageURL;
    private String bannerImageURL;
    private String category;

    public RedditPost(String title, String shortDescription, String imageURL) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.iconImageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = (title);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = HTMLEscape(shortDescription);
    }

    public String getIconImageURL() {
        return iconImageURL;
    }

    public void setIconImageURL(String iconImageURL) {
        this.iconImageURL = iconImageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = HTMLEscape(longDescription);
    }

    public void setDateFromStringUTC(long timestapm) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestapm * 1000);
        this.date = DateFormat.format("dd-MM-yyyy", cal).toString();
    }

    public String getBannerImageURL() {
        return bannerImageURL;
    }

    public void setBannerImageURL(String bannerImageURL) {
        this.bannerImageURL = bannerImageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category.isEmpty() || category.toLowerCase().equals("null")) {
            category = "";
        }
        this.category = category;
    }

    private String HTMLEscape(String html) {
        return Html.fromHtml(html).toString();
    }

}
