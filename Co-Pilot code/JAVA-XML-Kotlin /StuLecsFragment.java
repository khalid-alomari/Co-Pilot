package com.example.khalid.CoPilot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;


public class StuLecsFragment extends Fragment {

    private Button button;
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private EditText editText;
    private ArrayAdapter<String> stringArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stu_lecs, container, false);


//public void onActivityCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//    ListAdapter adapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,values);
//    setListAdapter(adapter);
//    getListView().setOnItemClickListener(this);
//}

        return inflater.inflate(R.layout.fragment_stu_lecs, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final privSingleton[] var = {privSingleton.getInstance()};
        //BackgroundTask backgroundTask = new BackgroundTask(getActivity());
        // backgroundTask.execute("lecs", var.getIDValue());

        //final ArrayAdapter<String> stringArrayAdapter;
        final ArrayList<String> AdList = new ArrayList<>();
        final ArrayList<String> stringArrayList = var[0].getlist();
        int dateCounter = 0;
        if (stringArrayList.size() != 0) {
            for (int i = 0; i < 6; i++) {

                final ArrayAdapter<String> stringArrayAdapter;
                String idOfList = "lecList" + String.valueOf(i);
                int leclist = getResources().getIdentifier(idOfList, "id", getContext().getPackageName());
                listView = (ListView) getView().findViewById(leclist);
                ArrayList<String> aList = new ArrayList<String>();
                for (int j = 0; j < stringArrayList.size() / 6; j++) {
                    if (i == 2) {
                        String date = stringArrayList.get(i + (6 * j) + dateCounter) + "-" + stringArrayList.get(i + (6 * j) + 1 + dateCounter);
                        dateCounter++;
                        aList.add(date);
                    } else {
                        if(i==4){
                            AdList.add (stringArrayList.get(i + (6 * j) + (dateCounter - (dateCounter - (j + 1))) - ((i < 2) ? 1 : 0)));
                            Log.d("i==4test",(stringArrayList.get(i + (6 * j) + (dateCounter - (dateCounter - (j + 1))) - ((i < 2) ? 1 : 0))));
                        }else {
                            aList.add(stringArrayList.get(i + (6 * j) + (dateCounter - (dateCounter - (j + 1))) - ((i < 2) ? 1 : 0)));
                            Log.d("i==4test2",(stringArrayList.get(i + (6 * j) + (dateCounter - (dateCounter - (j + 1))) - ((i < 2) ? 1 : 0))));
                        }
                    }
                }
                stringArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.customlistitem0, aList);
                listView.setAdapter(stringArrayAdapter);
                stringArrayAdapter.notifyDataSetChanged();

            }
        }


        ListAdapter listAdapter = new ArrayAdapter<String>(getContext(), R.layout.customlistitem0,AdList);
        ListView listViewAbs = (ListView) getView().findViewById(R.id.lecList4);
        listViewAbs.setAdapter(listAdapter);
        listViewAbs.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       // String cities = String.valueOf(parent.getItemAtPosition(position));
                       // Toast.makeText(stringArrayList.this, cities, Toast.LENGTH_LONG).show();
                       // Log.d("positiontest", String.valueOf(position));
                        var[0] = privSingleton.getInstance();
                        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                        ArrayList<String> list2 = var[0].getlist2();
//                        Log.d("positiontest2",list2.get(position));
                       // Log.d("positiontest3",var[0].getIDValue());
                        backgroundTask.execute("abs", var[0].getIDValue(), String.valueOf(position+1));
                        sleep(1500);
                            //code specific to first list item
                            Intent intent = new Intent(getActivity(), AbsenceActivity.class);
                            startActivity(intent);



                    }


                });

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

