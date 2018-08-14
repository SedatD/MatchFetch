package com.matchandfetch.matchfetch.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SD
 * on 6.08.2018.
 */

public class UserOtherImage {

    private static final String TAG = "UserOtherImage ";

    private int userID, userImageID;
    private String image;
    private String addDate;

    public UserOtherImage(JSONObject response) {
        try {
            this.userID = response.getInt("userID");
            this.userImageID = response.getInt("userImageID");
            this.image = response.getString("image");
            this.addDate = response.getString("addDate");
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    public int getUserImageID() {
        return userImageID;
    }

    public String getImage() {
        return image;
    }

    public String getAddDate() {
        return addDate;
    }

}
