package com.example.hshack.circadian;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.example.hshack.circadian.R;

import java.util.ArrayList;
import java.util.Arrays;

public class NotificationFragment extends Fragment {
    ListView listView;
    private ListView mainListView;
    private ListFragment lf;
    private ArrayAdapter<SleepTime> listAdapter;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.fragment_notification);

        // Find the ListView resource.
        View v = inflater.inflate(R.layout.fragment_notification, container, false);


        SleepDbHelper sdh = new SleepDbHelper(getContext());
        ArrayList<SleepTime> sleepTimes = sdh.getEntriesByDate();

        listAdapter = new ArrayAdapter<SleepTime>(getActivity(), R.layout.list_text_view, sleepTimes);
       //System.out.println(listAdapter.getCount());


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView = (ListView) v.findViewById(R.id.list_view);
        mainListView.setAdapter(listAdapter);
        //Log.d("**", String.valueOf(mainListView.getCount()));

        System.out.println(mainListView);
        return v;
    }
}