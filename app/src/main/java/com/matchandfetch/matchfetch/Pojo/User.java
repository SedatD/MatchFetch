package com.matchandfetch.matchfetch.Pojo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SD
 * on 6.08.2018.
 */

public class User {

    private static final String TAG = "UserPojo ";

    private int userID;
    private String name, sirname, email, password, phoneNumber, age;
    private int gender;
    private String image;
    private int status, premium;
    private String regDate, visitDate;
    private String oneSignalID;
    private int coordinateRange;
    private boolean userDataIsEqual;
    private String likeStuff;
    private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public User(JSONObject response, boolean isLogin) {
        try {
            this.userID = response.getInt("userID");
            this.name = response.getString("name");
            this.sirname = response.getString("sirname");
            this.age = response.getString("age");
            this.image = response.getString("image");
            this.oneSignalID = response.getString("oneSignalID");
            if (isLogin) {
                this.email = response.getString("email");
                this.password = response.getString("password");
                this.phoneNumber = response.getString("phoneNumber");
                this.gender = response.getInt("gender");
                this.status = response.getInt("status");
                this.premium = response.getInt("premium");
                this.regDate = response.getString("regDate");
                this.visitDate = response.getString("visitDate");
                this.coordinateRange = response.getInt("coordinateRange");
                //this.login = response.getBoolean("login");
            } else {
                this.userDataIsEqual = response.getBoolean("userDataIsEqual");
                this.likeStuff = response.getString("LikeStuff");
                JSONArray jsonArray = response.getJSONArray("UserOtherImageLists");
                for (int i = 0; i < jsonArray.length(); i++) {
                    UserOtherImage userOtherImage = new UserOtherImage(jsonArray.getJSONObject(i));
                    userOtherImageArrayList.add(userOtherImage);
                }
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getSirname() {
        return sirname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public int getStatus() {
        return status;
    }

    public int getPremium() {
        return premium;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getOneSignalID() {
        return oneSignalID;
    }

    public int getCoordinateRange() {
        return coordinateRange;
    }

    public boolean isUserDataIsEqual() {
        return userDataIsEqual;
    }

    public String getLikeStuff() {
        return likeStuff;
    }

    public ArrayList<UserOtherImage> getUserOtherImageArrayList() {
        return userOtherImageArrayList;
    }

}
