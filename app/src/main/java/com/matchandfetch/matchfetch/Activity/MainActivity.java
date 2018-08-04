package com.matchandfetch.matchfetch.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.matchandfetch.matchfetch.Adapter.MFCard;
import com.matchandfetch.matchfetch.Pojo.Profile;
import com.matchandfetch.matchfetch.R;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import static com.matchandfetch.matchfetch.Util.CalculationUtils.loadProfiles;

public class MainActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.mf_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.mf_swipe_out_msg_view));


        for (Profile profile : loadProfiles(mContext)) {
            mSwipeView.addView(new MFCard(mContext, profile, mSwipeView));
        }

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

    }

}
