package com.example.khalid.CoPilot;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements LogoutListener {

    private static BaseActivity mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";




    private BaseActivity(Context context) {
        mCtx = context;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        ((MyApp) getApplication ()).registerSessionListener ( this );
        ((MyApp) getApplication ()).startUserSession ();
    }

    public static synchronized BaseActivity getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BaseActivity( context );
        }
        return mInstance;
    }

    public boolean userLogin(int id, String username, String email) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences ( SHARED_PREF_NAME, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit ();

        editor.putInt ( KEY_USER_ID, id );
        editor.putString ( KEY_USER_EMAIL, email );
        editor.putString ( KEY_USERNAME, username );

        editor.apply ();

        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences ( SHARED_PREF_NAME, Context.MODE_PRIVATE );
        if (sharedPreferences.getString ( KEY_USERNAME, null ) != null) {
            return true;
        }
        return false;
    }

    public static boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences ( SHARED_PREF_NAME, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit ();
     //   Intent i = new Intent ( BaseActivity.this, MainActivity.class );//BaseActivity.this
     //   startActivity ( i );// startActivity (new Intent (this, MainActivity.class));
        editor.clear ();
        editor.apply ();
       // finish ();


        return true;
    }




   /* public String getUsername() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences ( SHARED_PREF_NAME, Context.MODE_PRIVATE );
        return sharedPreferences.getString ( KEY_USERNAME, null );
    }

    public String getUserEmail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences ( SHARED_PREF_NAME, Context.MODE_PRIVATE );
        return sharedPreferences.getString ( KEY_USER_EMAIL, null );
    }*/


    @Override
    public void onUserInteraction() {
        (( MyApp ) getApplication ()).onUserInteracted ();
    }


    @Override
    public void onSessionLogout() {
       // if (!isLoggedIn()) {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();


            Intent i = new Intent(BaseActivity.this, MainActivity.class);//BaseActivity.this
            startActivity(i);// startActivity (new Intent (this, MainActivity.class));
            editor.clear();
            editor.apply();
            finish();
        }
  //  }
      //  else
       //{
     //       finish ();
       // Intent i = new Intent ( BaseActivity.this, MainActivity.class );//BaseActivity.this
       // startActivity ( i );// startActivity (new Intent (this, MainActivity.class));
       // }
   // }

   // public void Settings()
    //{
      //  Intent i = new Intent(BaseActivity.this, settings.class);
        //  Intent i = new Intent(getApplicationContext(), MainActivity.class);
      //  startActivity(i);
    //}



}
