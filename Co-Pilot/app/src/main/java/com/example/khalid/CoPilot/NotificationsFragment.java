package com.example.khalid.CoPilot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final privSingleton[] var = {privSingleton.getInstance()};
        //BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        // backgroundTask.execute("lecs", var.getIDValue());
        //final ArrayAdapter<String> stringArrayAdapter;

        final ArrayList<String> AdList = new ArrayList<>();
        final ArrayList<String> stringArrayList = var[0].getlist();

        for (String s : stringArrayList
                ) {
            Log.d("looptest230", s);
        }
        if (stringArrayList.size() != 0) {

            final ArrayAdapter<String> stringArrayAdapter;
            int Nlist = getResources().getIdentifier("notifyList", "id", getContext().getPackageName());
            listView = (ListView) getView().findViewById(Nlist);
            ArrayList<String> aList = new ArrayList<String>();
            for (int j = 0; j < stringArrayList.size() / 2; j++) {

                String value = stringArrayList.get(2 * j + 1) + ": " + stringArrayList.get((2 * j));
                aList.add(value);
                Log.d("valuetest", value);

            }
            stringArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.customlistitem1, aList);
            listView.setAdapter(stringArrayAdapter);
            stringArrayAdapter.notifyDataSetChanged();


        }

        //listView=(ListView) getView().findViewById(R.id.lecList0);
        // stringArrayAdapter =new ArrayAdapter<String>(getContext(), R.layout.customlistitem0,stringArrayList);
        // listView.setAdapter(stringArrayAdapter);
        // stringArrayAdapter.notifyDataSetChanged();

        // int test = getResources().getIdentifier("lecList0","id",getContext().getPackageName());
        // Log.d("test", String.valueOf(test));
        //   test = getResources().getIdentifier("lecList1","id",getContext().getPackageName());
        // Log.d("test", String.valueOf(test));
    }


}
