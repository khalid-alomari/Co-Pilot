package com.example.khalid.CoPilot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StuEntFragment extends Fragment {
    @Nullable
//@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containor, @Nullable Bundle savedInstanceState) {

        // startActivity ( new Intent ( StuEntFragment.this, StuEntFragment.class ) );
        Intent intent = new Intent(getActivity(), nfcActivity.class);
        startActivity(intent);

        return inflater.inflate ( R.layout.activity_sn, containor, false );




    }

}