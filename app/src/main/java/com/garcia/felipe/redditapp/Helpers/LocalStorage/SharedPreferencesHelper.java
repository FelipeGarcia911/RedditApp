package com.garcia.felipe.redditapp.Helpers.LocalStorage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    public static SharedPreferencesHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize(Activity activity) {
        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void write(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String read(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    private static class SingletonHolder {
        private static final SharedPreferencesHelper INSTANCE = new SharedPreferencesHelper();
    }

}
