package com.garcia.felipe.redditapp.Helpers;

import android.app.ProgressDialog;
import android.content.Context;

public class SimpleProgressDialogHelper {
    private final ProgressDialog progressDialog;

    public SimpleProgressDialogHelper(Context context) {
        this.progressDialog = new ProgressDialog(context);
    }

    public void setTitle(String title) {
        progressDialog.setTitle(title);
    }

    public void setMessage(String message) {
        progressDialog.setMessage(message);
    }

    public void showProgressDialog() {
        progressDialog.show();
    }

    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    public void setCancelable(boolean cancelable) {
        progressDialog.setCancelable(cancelable);
        progressDialog.setCanceledOnTouchOutside(cancelable);
    }
}
