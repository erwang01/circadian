package com.example.hshack.circadian;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
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
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        mProgress = (ProgressBar) v.findViewById(R.id.progressBar);

        ToggleButton toggle = (ToggleButton) v.findViewById(R.id.button);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    beginTrack();
                } else {
                    stopTrack(v);
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

    public void stopTrack(View v) {
        getActivity().stopService(new Intent(getActivity(), BackgroundStopwatch.class));
        animation.pause();

        TextView tv = (TextView)v.findViewById(R.id.textView2);
        tv.setVisibility(TextView.GONE);
        ToggleButton tb = (ToggleButton)v.findViewById(R.id.button);
        tb.setVisibility(View.GONE);
        ProgressBar pb = (ProgressBar)v.findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        displayMessage(v);
    }

    public void displayMessage(View v) {
        TextView textView = (TextView)v.findViewById(R.id.hours);
        SleepDbHelper sdh = new SleepDbHelper(getContext());
        SleepTime sleepTime = sdh.getEntry(sdh.getEntryCount()-1);
        Log.d("****", String.valueOf(sleepTime.getDuration()));
        textView.setText(String.valueOf(String.format("%.5f", (double)sleepTime.getDuration()/1000.0/60.0/60.0)));
        textView.setVisibility(TextView.VISIBLE);
        TextView tv = (TextView)v.findViewById(R.id.congrats);
        tv.setVisibility(TextView.VISIBLE);
        TextView tv2 = (TextView)v.findViewById(R.id.hours_label);
        tv2.setVisibility(TextView.VISIBLE);
    }
}