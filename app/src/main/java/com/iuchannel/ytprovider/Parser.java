package com.iuchannel.ytprovider;

import android.database.Cursor;
import android.database.MatrixCursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chanyhan on 2017. 7. 6..
 */

public class Parser {

    public static final String PRJ_ID = "_id";
    public static final String PRJ_TITLE = "title";

    public static final String[] COLUMN_NAMES = {
            PRJ_ID,
            PRJ_TITLE,
    };

    public static Cursor convertString2Cursor(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return convertJson2Cursor(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cursor convertJson2Cursor(JSONObject jsonObject) {
        MatrixCursor cursor = new MatrixCursor(COLUMN_NAMES, 8);
        return cursor;
    }
}
