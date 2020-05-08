package com.example.khalid.CoPilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AbsenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);
        privSingleton var = privSingleton.getInstance();
        //Toast.makeText(this, var.getIDValue().toString(), Toast.LENGTH_LONG).show();


        ListView listView;
        //final ArrayAdapter<String> stringArrayAdapter;
        final ArrayList<String> stringArrayList = var.getlist();


        for (String s: stringArrayList
                ) {
            Log.d("abstest33",s);
        }

        if (stringArrayList.size() != 0) {
            for (int i = 0; i < 3; i++)
            {
                ArrayAdapter<String> stringArrayAdapter;
                String idOfList = "abslist" + String.valueOf(i);
                int abslist = getResources().getIdentifier(idOfList, "id", this.getPackageName());
                listView = (ListView) findViewById(abslist);
                ArrayList<String> aList = new ArrayList<String>();
                for (int j = 0; j < stringArrayList.size() / 3; j++)
                {
                    Log.d("loop",String.valueOf(j));
                    aList.add(stringArrayList.get(i + 3 * j));
                    Log.d("loop3",stringArrayList.get(i + 3 * j));
                    Log.d("loop2",aList.get(j));
                }
                for (String s: aList
                        ) {
                    Log.d("alisttest",s);
                }
                stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.customlistitem0, aList);
                listView.setAdapter(stringArrayAdapter);
                stringArrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
