package com.example.khalid.CoPilot;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {
    EditText User_ID, Password;
    String uid, pwd;
    private static Button button;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_ID = (EditText) (findViewById(R.id.login_uid));
        Password = (EditText) (findViewById(R.id.login_pwd));

        button = findViewById(R.id.are_you_human_button);
        mProgressbar = findViewById(R.id.progressbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyNet.getClient(MainActivity.this).verifyWithRecaptcha(getResources().getString(R.string.site_key)).addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                        validateRecaptchaFromServer(recaptchaTokenResponse.getTokenResult());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }

    private void validateRecaptchaFromServer(String token) {
        mProgressbar.setVisibility(View.VISIBLE);

        Call<RecaptchaResponse> call = ApiClient.getApiClient().create(ApiInterface.class).validateRecaptcha(token, Password.toString());

        call.enqueue(new Callback<RecaptchaResponse>() {
            @Override
            public void onResponse(Call<RecaptchaResponse> call, Response<RecaptchaResponse> response) {

                if (response.body() != null) {

                    if (response.body().getStatus().equals("ok")) {

                       // Intent intent = new Intent(MainActivity.this, ResponseActivity.class);
                       // intent.putExtra("message", response.body().getMessage());
                       // startActivity(intent);
                        mProgressbar.setVisibility(View.GONE);
                        Button btn = (Button) findViewById(R.id.sign2);
                        btn.setVisibility(View.VISIBLE);
                        Button btn2 = (Button) findViewById(R.id.are_you_human_button);
                        btn2.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        mProgressbar.setVisibility(View.GONE);
                    }

                }

            }

            @Override
            public void onFailure(Call<RecaptchaResponse> call, Throwable t) {

            }
    });
}


    //public void userReg(View view) {
       // startActivity(new Intent(this, MainActivity.class));
   // }

    public void userLogin(View view) {


        Log.d("logintest","Inside");
        privSingleton var = privSingleton.getInstance();
        int c = var.getLoginCounter();
        if(c >2){
            var.setLoginCounter(0);
            Toast.makeText(this, "Too many incorrect logins, please solve this CAPTCHA", Toast.LENGTH_SHORT).show();
            Log.d("logintest","Inside c>2");
            Button btn = (Button)  findViewById(R.id.are_you_human_button);
            btn.setVisibility(View.VISIBLE);
            Button btn2 = (Button)  findViewById(R.id.sign2);
            btn2.setVisibility(INVISIBLE);
        } else {
            uid = User_ID.getText().toString();
            pwd = Hashing.sha256().hashString(Password.getText().toString(), Charsets.UTF_8).toString();
            var = privSingleton.getInstance();
            var.setPwdHash(pwd);
            String method = "login";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, uid, pwd);
            c+=1;
            var.setLoginCounter(c);
            Log.d("logintest","c = " + String.valueOf(c));
        }


       /* uid = User_ID.getText().toString();
        pwd = Hashing.sha256().hashString(Password.getText().toString(), Charsets.UTF_8).toString();
        var = privSingleton.getInstance();
        var.setPwdHash(pwd);
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, uid, pwd);*/




        //startActivity(new Intent(this,Main2Activity.class));
    }
    /*public void btnSign(View view)
    {
        @SuppressLint("WrongViewCast") EditText txtuser = (EditText)findViewById ( R.id.User_ID );
        @SuppressLint("WrongViewCast") EditText txtpasswod = (EditText)findViewById ( R.id.LoginPassword );

        Intent myintent = new Intent (this.Main2Activity.class);

    }
    /*   public void sign (View view){
            EditText txtuser=(EditText)findViewById(R.id.email);
            EditText txtpass=(EditText)findViewById(R.id.password);

            android.content.Intent myitent;
            myitent = new android.content.Intent (LoginActivity.this, MainActivity.class);

            Bundle b= new Bundle();
            b.putString("email",txtuser.getText().toString());
            b.putString("password",txtuser.getText().toString());
            myitent.putExtras(b);// or myitent.putExtra("email",txtuser.getText().toString()).....;


    startActivity(myitent);*/


    // public void yourMethodName(View v){
    //  startActivity(new  android.content.Intent(LoginActivity.this, MainActivity.class));
    //  }

    //}*/

    /*public void btnsign(View v) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        //  Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }*/

    public void Forget(View v) {
        Intent i = new Intent(MainActivity.this, ForgetPassword.class);
        //  Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void SignUP(View v) {
        Intent i = new Intent(MainActivity.this, SignUP.class);
        //  Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
