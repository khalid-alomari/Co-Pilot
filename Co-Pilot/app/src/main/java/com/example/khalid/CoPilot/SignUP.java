package com.example.khalid.CoPilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class SignUP extends AppCompatActivity {

    EditText Username, User_ID, Password, PhoneNo, Email, Confirmpwd;
    String usrname,pwd,eml, usrid, phno;
    Button reg;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign_up );
        Username = (EditText)findViewById(R.id.reguser);
        User_ID = (EditText)findViewById(R.id.reguid);
        Password = (EditText)findViewById(R.id.regpwd);
        PhoneNo = (EditText)findViewById(R.id.regphoneno);
        Email = (EditText)findViewById(R.id.regemail);
        Confirmpwd = (EditText)findViewById(R.id.regconfpwd);
        reg = (Button)findViewById(R.id.button);
        updateUI();

    }

    private void updateUI() {
        awesomeValidation.addValidation(SignUP.this, R.id.reguser, "[a-zA-Z]{6,}", R.string.UsernameErr);
        awesomeValidation.addValidation(SignUP.this, R.id.reguid, "^[2][0][0-9]{6}$", R.string.User_IDErr);
        awesomeValidation.addValidation(SignUP.this,R.id.regemail,android.util.Patterns.EMAIL_ADDRESS,R.string.EmailErr);
        awesomeValidation.addValidation(SignUP.this,R.id.regphoneno, "^[0][7][7-9][\\d]{7}",R.string.PhoneNoErr);
        awesomeValidation.addValidation(SignUP.this,R.id.regpwd,".{4,}",R.string.PasswordErr);
        awesomeValidation.addValidation(SignUP.this,R.id.regconfpwd,R.id.regpwd,R.string.ConfirmpwdErr);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if (awesomeValidation.validate()){

                    pwd = Hashing.sha256().hashString(Password.getText().toString(), Charsets.UTF_8).toString();
                    usrname = Username.getText().toString();
                    usrid = User_ID.getText().toString();
                    phno = PhoneNo.getText().toString();
                    eml = Email.getText().toString();
                    String priv = "0";
                    String method = "register";
                    BackgroundTask backgroundTask = new BackgroundTask(SignUP.this);
                    backgroundTask.execute(method, usrid, usrname, pwd, phno, eml,priv);
                    finish();
                } else {
                    Toast.makeText(SignUP.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
