package com.example.khalid.CoPilot;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        privSingleton var = privSingleton.getInstance();
        //Toast.makeText(this, var.getIDValue().toString(), Toast.LENGTH_LONG).show();
        int pos = var.getPostion();
        final ArrayList<String> stringArrayList = var.getlist2();
        final ArrayList<String> stringArrayList3 = var.getlist3();
        Log.d("poooos", String.valueOf(pos));
        for (String s : stringArrayList3
                ) {
            Log.d("looplogin", s);

        }

        for (String s : stringArrayList
                ) {
            Log.d("looplogin2", s);

        }
        uid = stringArrayList3.get(pos);

        ListView listView;
        //final ArrayAdapter<String> stringArrayAdapter;


        TextView tv = (TextView) findViewById(R.id.namelog);
        TextView tv2 = (TextView) findViewById(R.id.startlog);
        TextView tv3 = (TextView) findViewById(R.id.endlog);
        tv.setText(uid);
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (stringArrayList.get(i).matches(uid)) {
                Log.d("testtest", uid);
                Log.d("testtes2", stringArrayList.get(i));
                tv2.setText(stringArrayList.get(i + 1));
                tv3.setText(stringArrayList.get(i + 2));
            }
        }

        Button btn = (Button) findViewById(R.id.logbtn);
        btn.setOnClickListener(new View.OnClickListener() {

                                   // @Override
                                   public void onClick(View arg0) {
                                       AlertDialog alertDialog;
                                       //alertDialog = new AlertDialog.Builder (getString()).create ();
                                       //alertDialog.setTitle ( "Settings fragment dialog" );
                                       //alertDialog.setMessage ( "Default." );
                                       privSingleton var = privSingleton.getInstance();


                                       EditText STime = (EditText) findViewById(R.id.sTimeET);
                                       final String STime2 = STime.getText().toString();
                                       EditText ETime = (EditText) findViewById(R.id.eTimeET);
                                       final String ETime2 = ETime.getText().toString();

                                       //if(STime2<ETime2){
                                       BackgroundTask backgroundTask = new BackgroundTask(TimeActivity.this);
                                       backgroundTask.execute("logchange", uid, "0", STime2);

                                       BackgroundTask backgroundTask2 = new BackgroundTask(TimeActivity.this);
                                       backgroundTask2.execute("logchange", uid, ETime2, "0");
                                       //}


                                       // startActivity( new Intent(TimeActivity.this, TimeActivity.this););


                                       finish();
                                       startActivity(getIntent());
                                       //Intent i = new Intent ( TimeActivity.this, TimeActivity.class );
                                       //startActivity ( i );

                                   }
                               }
        );
    }
}
