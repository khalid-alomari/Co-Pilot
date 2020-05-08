package com.example.khalid.CoPilot;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ListView;

import java.util.ArrayList;

public class InstLecsFragment extends Fragment {

    private Button button;
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private EditText editText;
    private  ArrayAdapter<String> stringArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inst_lecs, container, false);


    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        privSingleton var = privSingleton.getInstance();
      //  BackgroundTask backgroundTask = new BackgroundTask(getActivity());
      //  backgroundTask.execute("instlec",var.getIDValue()," ");


        //final ArrayAdapter<String> stringArrayAdapter;
        final ArrayList<String> stringArrayList = var.getlist();
        int dateCounter = 0;

        for (String s : stringArrayList)
        {
            Log.d("looptest",s);
        }



        if( stringArrayList.size() !=0 ) {
            for (int i = 0; i < 5; i++) {

                final ArrayAdapter<String> stringArrayAdapter;
                String idOfList = "instlecList" + String.valueOf(i);
                int leclist = getResources().getIdentifier(idOfList, "id", getContext().getPackageName());
                listView = (ListView ) getView().findViewById(leclist);
                ArrayList<String> aList = new ArrayList<String>();
                for (int j = 0; j < stringArrayList.size() / 5; j++) {
                    if(i==2){
                        //  Log.d("eshet3rfo", stringArrayList.get(i + (4 * j)+dateCounter));
                        // Log.d("eshet3rfo2", String.valueOf (i + (4 * j)+dateCounter));

                        // Log.d("eshet3rfo3", stringArrayList.get((i + (4 * j)+dateCounter)+1));
                        //Log.d("eshet3rfo4", String.valueOf ((i + (4 * j)+dateCounter)+1));

                        String date = stringArrayList.get(i + (5 * j)+dateCounter) + "-" + stringArrayList.get(i + (5 * j)+1+dateCounter);
                        dateCounter++;
                        aList.add(date);
                    } else {
                        Log.d("dsssss", stringArrayList.get ( (i + (5 * j) + (dateCounter - (dateCounter-(j+1))) - ((i < 2) ? 1 : 0)) ) );
                        Log.d("dsssss2", String.valueOf ( (i + (5 * j) + (dateCounter - (dateCounter-(j+1))) - ((i < 2) ? 1 : 0)) ) );
                        aList.add(stringArrayList.get(i + (5 * j) + (dateCounter - (dateCounter-(j+1))) - ((i < 2) ? 1 : 0)   ));
                    }
                }
                stringArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.customlistitem0, aList);
                listView.setAdapter(stringArrayAdapter);
                stringArrayAdapter.notifyDataSetChanged();

            }
        }
        final privSingleton[] var2 = {privSingleton.getInstance ()};
        final ArrayList<String> loginlist = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        list2 = var.getlist2 ();
        if (list2.size ()!=0){
            for (int i=0; i< (list2.size()/3 );i++){
                loginlist.add (list2.get(i*3));
            }
        }
        for (String s:loginlist
                ) {
            Log.d ( "looplogin5555",s );

        }
        var2[0].setlist3 (loginlist);
        ListView listViewLogin = (ListView) getView().findViewById(R.id.instlecList2);
        listViewLogin.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        var2[0].setPostion ( position );
                        Intent intent = new Intent(getActivity(), TimeActivity.class);
                        startActivity(intent);



                    }
                });

        ListView listViewStatus = (ListView) getView().findViewById(R.id.instlecList4);
        listViewStatus.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        final privSingleton var = privSingleton.getInstance();
                        final ArrayList<String> stringArrayList3 = var.getlist3();
                        final int pos = position;
                        final String uid = stringArrayList3.get(pos);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Change status");
                        builder.setMessage("Are you sure you want to toggle this lecture?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                                        backgroundTask.execute("updateStatus", uid);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });

        // stringArrayAdapter =new ArrayAdapter<String>(getContext(), R.layout.customlistitem0,stringArrayList);
        // listView.setAdapter(stringArrayAdapter);
        // stringArrayAdapter.notifyDataSetChanged();

        // int test = getResources().getIdentifier("lecList0","id",getContext().getPackageName());
        // Log.d("test", String.valueOf(test));
        //   test = getResources().getIdentifier("lecList1","id",getContext().getPackageName());
        // Log.d("test", String.valueOf(test));
    }


}


