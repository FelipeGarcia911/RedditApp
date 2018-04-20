package com.garcia.felipe.redditapp.HomeList.Events;

import android.content.Intent;

public class ImageEvent {

    private int requestCode;
    private int resultCode;
    private Intent data;

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
