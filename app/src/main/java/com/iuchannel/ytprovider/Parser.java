package com.iuchannel.ytprovider;

import android.database.Cursor;
import android.database.MatrixCursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chanyhan on 2017. 7. 6..
 */

public class Parser {

    private final static String CONST_DATA = "data";
    private final static String CONST_ITEMS = "items";

    public final static String PRJ_ID = "_id";
    public final static String PRJ_PLAYLIST_ID = "id";
    public final static String PRJ_TITLE = "title";
    public final static String PRJ_DESCRIPTION = "description";
    public final static String PRJ_SIZE = "size";
    public final static String PRJ_THUMNAIL = "thumbnail";
    public final static String PRJ_SQDEFAULT = "sqDefault";
    public final static String PRJ_HQDEFAULT = "hqDefault";
    public final static String PRJ_AUTHOR = "author";
    public final static String PRJ_CREATED = "created";
    public final static String PRJ_UPDATED = "updated";

    public final static String PRJ_VIDEO_ID = "id";
    public final static String PRJ_DURATION = "duration";

    // Access Control Feature for future implementation
    public final static String PRJ_ACCESS_CONTROL = "accessControl";
    public final static String PRJ_COMMENT = "comment";
    public final static String PRJ_COMMENT_VOTE = "commentVote";
    public final static String PRJ_VIDEO_RESPOND = "videoRespond";
    public final static String PRJ_RATE = "rate";
    public final static String PRJ_EMBED = "embed";
    public final static String PRJ_LIST = "list";
    public final static String PRJ_AUTOPLAY = "autoPlay";
    public final static String PRJ_SYNDICATE = "syndicate";

    // Restriction Feature for future implementation
    public final static String PRJ_RESTRICTION = "restriction";
    public final static String PRJ_TYPE = "type";
    public final static String PRJ_RELATIONSHIP = "relationship";
    public final static String PRJ_COUNTRIES = "countries";

    public final static String[] USER_PL_PRJ = {
            PRJ_ID,
            PRJ_PLAYLIST_ID,
            PRJ_CREATED,
            PRJ_UPDATED,
            PRJ_AUTHOR,
            PRJ_TITLE,
            PRJ_DESCRIPTION,
            PRJ_SIZE,
            PRJ_SQDEFAULT,
            PRJ_HQDEFAULT,
    };

    public static String[] PL_VIDEO_PRJ = {
            PRJ_ID,
            PRJ_VIDEO_ID,
            PRJ_TITLE,
            PRJ_DESCRIPTION,
            PRJ_HQDEFAULT,
            PRJ_SQDEFAULT,
            PRJ_DURATION,
            PRJ_SYNDICATE,
    };


    /*
        public static com.kakao.adfit.publisher.AdView getView(){
            com.kakao.adfit.publisher.AdView adView =  new com.kakao.adfit.publisher.AdView(null);
            adView.setAdUnitSize("");
            return adView;
        }
        */
    public static Cursor convertUsersItems2Cursor(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return convertUsersItems2Cursor(jsonObject);
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static Cursor convertUsersItems2Cursor(JSONObject object) {
        MatrixCursor matrixCursor = new MatrixCursor(USER_PL_PRJ);
        try {
            JSONObject j = object.getJSONObject(CONST_DATA);
            JSONArray ja = j.getJSONArray(CONST_ITEMS);
            for (int y = 0; y < ja.length(); y++) {
                Object[] input = new Object[USER_PL_PRJ.length];
                JSONObject tempObj = ja.getJSONObject(y);

                for (Integer z = 0; z < input.length; z++) {
                    int code = USER_PL_PRJ[z].hashCode();
                    if (PRJ_ID.hashCode() == code) {
                        input[z] = z;
                    } else if (PRJ_HQDEFAULT.hashCode() == code || PRJ_SQDEFAULT.hashCode() == code) {
                        JSONObject thumb = tempObj.getJSONObject(PRJ_THUMNAIL);
                        input[z] = thumb.optString(USER_PL_PRJ[z], "");
                    } else {
                        input[z] = tempObj.optString(USER_PL_PRJ[z], "");
                    }
                }
                matrixCursor.addRow(input);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return matrixCursor;
    }
}
