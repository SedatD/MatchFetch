package com.matchandfetch.matchfetch.Adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.matchandfetch.matchfetch.Pojo.User;
import com.matchandfetch.matchfetch.R;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by SD
 * on 29.07.2018.
 */

@Layout(R.layout.mf_card_view)
public class MFCard {

    private static final String TAG = "MFCard";

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    @View(R.id.linearLayout)
    private LinearLayout linearLayout;

    //private Profile mProfile;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    private User mUser;

    private int measure;

    /*public MFCard(Context context, Profile profile, SwipePlaceHolderView swipeView) {
        mContext = context;
        mProfile = profile;
        mSwipeView = swipeView;
    }*/


    public MFCard(Context context, User user, SwipePlaceHolderView swipeView) {
        mContext = context;
        mUser = user;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved() {
        /*linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                measure = (linearLayout.getWidth() - MyUtils.dpToPx(40));
                measure = (measure / 5);

                linearLayout.requestLayout();
            }
        });

        MyUtils.setMeasures(profileImageView, measure);*/

        Glide.with(mContext).load("http://sedatdilmac.com/img/profile.jpg").into(profileImageView);
        nameAgeTxt.setText(mUser.getName() + ", " + mUser.getAge());
        locationNameTxt.setText("location");
    }

    @SwipeOut
    private void onSwipedOut() {
        //Log.wtf(TAG, "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        //Log.wtf(TAG, "onSwipeCancelState");
        Log.wtf(TAG, "onSwipeCancelState this.mUser.getUserID() : " + this.mUser.getUserID());
    }

    @SwipeIn
    private void onSwipeIn() {
        //Log.wtf(TAG, "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState() {
        //Log.wtf(TAG, "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        //Log.wtf(TAG, "onSwipeOutState");
    }

}