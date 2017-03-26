package com.example.hshack.circadian;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ibm.watson.developer_cloud.conversation;

import com.example.hshack.circadian.R;

public class DashboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
        service.setUsernameAndPassword("<username>", "<password>");

        MessageRequest newMessage = new MessageRequest.Builder().inputText("Hi").build();
        MessageResponse response = service.message("<workspace-id>", newMessage).execute();
        System.out.println(response);

        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}