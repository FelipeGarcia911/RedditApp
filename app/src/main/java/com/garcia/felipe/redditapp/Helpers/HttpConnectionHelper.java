package com.garcia.felipe.redditapp.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class HttpConnectionHelper {

    public static final int SUCCESS = 200;
    public static final int NOT_FOUND = 404;
    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    public static final String TEXT_CONTENT_TYPE = "text/plain";

    public JSONObject stringBuilderJSONObject(byte[] responseBody) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(new String(responseBody));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONArray stringBuilderJSONArray(byte[] responseBody) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray(new String(responseBody));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public int stringCodeToInt(String stringCode) {
        return Integer.valueOf(stringCode);
    }

    public StringEntity createJsonRequestParams(HashMap<String, String> hashMap, String contentType) {
        StringEntity requestParams = null;
        try {
            JSONObject requestObject = new JSONObject();
            for (String name : hashMap.keySet()) {
                requestObject.put(name, hashMap.get(name));
            }
            requestParams = new StringEntity(requestObject.toString(), "UTF-8");
            requestParams.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestParams;
    }

}
