package com.example.hshack.circadian;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.ProgressBar;


public class MainFragment extends Fragment {
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    private ObjectAnimator animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mProgress = (ProgressBar) v.findViewById(R.id.progressBar);


       // IntentFilter filter = new IntentFilter("MY_ACTION");
        //getActivity().registerReceiver(myReceiver, filter);

        ToggleButton toggle = (ToggleButton) v.findViewById(R.id.button);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    beginTrack();
                } else {
                    stopTrack();
                }
            }
        });
        return v;
    }

    public void beginTrack() {
        animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 1000);
        animation.setDuration(1000000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        getActivity().startService(new Intent(getActivity(), BackgroundStopwatch.class));
    }

    /* private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mProgressStatus++;
            mProgress.setProgress(mProgressStatus);
        }
    }; */

    public void stopTrack() {
        getActivity().stopService(new Intent(getActivity(), BackgroundStopwatch.class));
        animation.pause();

    }

}