package com.garcia.felipe.redditapp.HomeList.Events;

import com.garcia.felipe.redditapp.Models.RedditPost;

import java.util.ArrayList;

public class ListEvent {

    public static final int ON_SUCCESS = 1;
    public static final int ON_FAILURE = 0;

    private int eventType;
    private String msgError;
    private ArrayList<RedditPost> list;

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

    public ArrayList<RedditPost> getList() {
        return list;
    }

    public void setList(ArrayList<RedditPost> list) {
        this.list = list;
    }
}
