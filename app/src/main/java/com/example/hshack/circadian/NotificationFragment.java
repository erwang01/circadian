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
    private ArrayAdapter<String> listAdapter;

    /** Called when the activity is first created. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.fragment_notification);

        // Find the ListView resource.
        View v = inflater.inflate(R.layout.fragment_notification, container, false);


        // Create and populate a List of planet names.
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.add("Mercury");
        planetList.add("Venus");
        planetList.add("Earth");
        //planetList.addAll(Arrays.asList(planets));

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_text_view, planetList);
       //System.out.println(listAdapter.getCount());


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView = (ListView) v.findViewById(R.id.list_view);
        mainListView.setAdapter(listAdapter);
        //Log.d("**", String.valueOf(mainListView.getCount()));

        System.out.println(mainListView);
        return v;
    }
}