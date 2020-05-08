package com.example.khalid.CoPilot;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

public class MyApp extends Application {
    private LogoutListener listener;
    private Timer timer;

    public void startUserSession()
    {
        cancelTimer();

        timer = new Timer();
        timer.schedule( new TimerTask () {
            @Override
            public void run() {
                listener.onSessionLogout ();


            }
        }, 600000);// 15 seconds
    }

    private void cancelTimer() {
        if (timer !=null) timer.cancel ();
    }

    public void registerSessionListener(LogoutListener listener) {
        this.listener = listener;
    }

    public void onUserInteracted() {
        startUserSession ();
    }
   /* public  User getUSer()
    {

    }
    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPrefrences(SHARED_PREF_NAME, Context.MORE_PRIVATE);
        SharedPreferences.Editor editor = SharedPreferences.edit();
        editor.clear ();
        editor.apply ();
    }*/

}
