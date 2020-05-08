package com.example.khalid.CoPilot;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import static android.os.SystemClock.sleep;

public class SettingsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.fragment_settings, container, false);

       // final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Change Email:






        Button btn = (Button) RootView.findViewById(R.id.settingsBtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Settings fragment dialog");
                alertDialog.setMessage("Default.");
                privSingleton var = privSingleton.getInstance();

                EditText SettingsPwdET = (EditText) RootView.findViewById(R.id.SettingsPwd);
                final String settingsPwdS = Hashing.sha256().hashString(SettingsPwdET.getText().toString(), Charsets.UTF_8).toString(); //Change email PWD

                EditText SettingsEmailET = (EditText) RootView.findViewById(R.id.SettingsEmail);
                final String settingsEmailS = SettingsEmailET.getText().toString();//Change email email

                //Change password:
                EditText SettingsPwd2ET = (EditText) RootView.findViewById(R.id.SettingsPwd2);
                final String settingsPwd2S = Hashing.sha256().hashString(SettingsPwd2ET.getText().toString(), Charsets.UTF_8).toString(); //Change pwd oldPWD

                final EditText SettingsNewPwdET = (EditText) RootView.findViewById(R.id.SettingsNewPwd);
                final String settingsNewPwdS = Hashing.sha256().hashString(SettingsNewPwdET.getText().toString(), Charsets.UTF_8).toString(); //Change pwd newPWD

                EditText SettingsCnfrmPwdET = (EditText) RootView.findViewById(R.id.SettingsCnfrmPwd);
                final String settingsCnfrmPwdS = Hashing.sha256().hashString(SettingsCnfrmPwdET.getText().toString(), Charsets.UTF_8).toString(); //Change pwd confirmPWD
                if(!SettingsPwdET.getText().toString().matches("") && !SettingsEmailET.getText().toString().matches("")){

                    if(!settingsPwdS.matches(var.getPwdHash())){
                        Toast.makeText(getContext(), "Wrong password !", Toast.LENGTH_LONG).show();
                       // alertDialog.setMessage("Password is wrong1.");
                       // alertDialog.show();
                    } else {
                        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
                        awesomeValidation.addValidation(getActivity(),R.id.SettingsEmail,android.util.Patterns.EMAIL_ADDRESS,R.string.EmailErr);
                        if(awesomeValidation.validate()) {
                            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                            backgroundTask.execute("update", var.getIDValue(), "0", settingsEmailS);
                            Toast.makeText(getContext(), "Email Updated.", Toast.LENGTH_LONG).show();
                        }
                    }


                } else if (!SettingsPwd2ET.getText().toString().equals("") && !SettingsNewPwdET.getText().toString().equals("") && !SettingsCnfrmPwdET.getText().toString().equals("")){

                    if(!settingsPwd2S.equals(var.getPwdHash())){
                        Toast.makeText(getContext(), "Wrong password !", Toast.LENGTH_LONG).show();
                       // alertDialog.setMessage("Password is wrong.");
                        //alertDialog.show();

                    } else {
                        AwesomeValidation awesomeValidation2 = new AwesomeValidation(ValidationStyle.BASIC);
                        awesomeValidation2.addValidation(getActivity(),R.id.SettingsNewPwd,".{4,}",R.string.PasswordErr);
                        awesomeValidation2.addValidation(getActivity(),R.id.SettingsCnfrmPwd,R.id.SettingsNewPwd,R.string.ConfirmpwdErr);

                        if(awesomeValidation2.validate()){
                            BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                            backgroundTask.execute("update", var.getIDValue(),settingsNewPwdS, "0");
                            Toast.makeText(getContext(), "Password updated !", Toast.LENGTH_LONG).show();
                            sleep(1500);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                } else {
                    Toast.makeText(getContext(), "Some values are missing !", Toast.LENGTH_LONG).show();
                   // alertDialog.setMessage("Some values are missing");
                   // alertDialog.show();
                }
                //alertDialog.setMessage("fail");
                //alertDialog.show();

            }
        });


        return RootView;

    }
}
