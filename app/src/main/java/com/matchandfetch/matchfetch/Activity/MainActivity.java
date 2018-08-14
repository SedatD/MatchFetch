package com.matchandfetch.matchfetch.Activity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.matchandfetch.matchfetch.Adapter.MFCard;
import com.matchandfetch.matchfetch.Pojo.User;
import com.matchandfetch.matchfetch.R;
import com.matchandfetch.matchfetch.AqRequest.AqJSONObjectRequest;
import com.matchandfetch.matchfetch.Util.MyApplication;
import com.matchandfetch.matchfetch.Util.MyUtils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.matchandfetch.matchfetch.Util.StaticFields.BASE_URL;
import static com.matchandfetch.matchfetch.Util.StaticFields.HASH;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAct ";

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        int bottomMargin = MyUtils.dpToPx(160); // if there is some view of size 160 dp
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setViewWidth(size.x)
                        //.setViewHeight(size.y - bottomMargin)
                        .setViewHeight(size.y)
                        .setSwipeInMsgLayoutId(R.layout.mf_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.mf_swipe_out_msg_view));


        /*for (Profile profile : loadProfiles(mContext)) {
            mSwipeView.addView(new MFCard(mContext, profile, mSwipeView));
        }*/

        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        String loginResponse = MyApplication.get().getPreferences().getString("loginResponse", null);
        if (loginResponse != null) {
            try {
                JSONObject jsonObject = new JSONObject(loginResponse);
                User user = new User(jsonObject, true);
                requestJson(user.getUserID());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void requestJson(int userID) {
        JSONObject params = new JSONObject();
        try {
            params.put("userid", userID);
            params.put("premium", 1);
            params.put("aqGokhan", 1);
            params.put("hash", HASH);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);
                    try {
                        JSONArray jsonArray = response.getJSONArray("aqArrayi");
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            User user = new User(jsonObject, false);
                            userArrayList.add(user);
                            mSwipeView.addView(new MFCard(mContext, user, mSwipeView));
                        }
                    } catch (JSONException e) {
                        Log.wtf(TAG, "response catch e.getMessage() : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "onErrorResponse : " + error);
                }
            };

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "userList.php", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
