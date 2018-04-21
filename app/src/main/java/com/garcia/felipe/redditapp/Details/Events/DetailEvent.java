package com.garcia.felipe.redditapp.Details.Events;

import com.garcia.felipe.redditapp.Models.RedditPost;

public class DetailEvent {

    public static final int ON_DETAIL_REQUEST = 0;

    private int eventType;
    private String msgError;
    private RedditPost item;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public RedditPost getItem() {
        return item;
    }

    public void setItem(RedditPost item) {
        this.item = item;
    }
}
