package com.example.hshack.circadian;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hshack.circadian.R;

import java.text.DateFormat;
import java.util.Date;

public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
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
        getActivity().startService(new Intent(getActivity(), BackgroundStopwatch.class));
    }

    public void stopTrack() {
        getActivity().stopService(new Intent(getActivity(), BackgroundStopwatch.class));
    }

}