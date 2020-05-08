package com.example.khalid.CoPilot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotificationsFragment_inst extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.fragment_notification_inst, container, false);

        Button btn = (Button) RootView.findViewById(R.id.button_notif);
        Log.d("insideclick","outside");
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                privSingleton var = privSingleton.getInstance();
            Log.d("insideclick","inside");
                EditText textET = (EditText) RootView.findViewById(R.id.textNotif);
                final String textS = textET.getText().toString();

                EditText lecidET = (EditText) RootView.findViewById(R.id.lecidNotif);
                final String lecidS = lecidET.getText().toString();

                if(!textET.getText().toString().matches("") && !lecidET.getText().toString().matches((""))){
                            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                            backgroundTask.execute("sendnotif", lecidS, textS, var.getIDValue());
                            //Toast.makeText(getContext(), "Notifications sent.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "Some values are missing !", Toast.LENGTH_LONG).show();
                }
            }
        });

        return RootView;

    }



}
