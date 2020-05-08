package com.example.khalid.CoPilot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SN extends AppCompatActivity {
    // Open new connection 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sn );
        Toolbar toolbar = ( Toolbar ) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );
        Snew v = new Snew();



    }

}
