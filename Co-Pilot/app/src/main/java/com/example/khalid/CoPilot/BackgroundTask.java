package com.example.khalid.CoPilot;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static android.support.v4.content.ContextCompat.getSystemService;
import static android.view.View.INVISIBLE;
import static android.view.View.generateViewId;

public class BackgroundTask extends AsyncTask<String, Void, String> {
    static Context ctx;
    AlertDialog alertDialog;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    public static View getLayoutByRes(@LayoutRes int layoutRes, @Nullable ViewGroup viewGroup)
    {
        final LayoutInflater factory = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return factory.inflate(layoutRes, viewGroup);
    }

    @Override
    protected void onPreExecute() {
        if (!(this instanceof BackgroundTask)) {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login information..");
        }
    }

    @Override
    protected String doInBackground(String... params) {
        // String reg_url = "http://10.0.2.2/copilot/register.php";
        String reg_url = "http://quotidian-twins.000webhostapp.com/register.php";
        String login_url = "http://quotidian-twins.000webhostapp.com/login.php";
        String lec_url = "http://quotidian-twins.000webhostapp.com/lecs.php";
        String abs_url = "http://quotidian-twins.000webhostapp.com/abs.php";
        String ent_url = "http://quotidian-twins.000webhostapp.com/ent.php";
        String update_url = "http://quotidian-twins.000webhostapp.com/update.php";
        String check_url = "http://quotidian-twins.000webhostapp.com/checkNotify.php";
        String inst_url = "http://quotidian-twins.000webhostapp.com/instlec.php";
        String log_change = "http://quotidian-twins.000webhostapp.com/logchange.php";
        String updateStatus_url = "http://quotidian-twins.000webhostapp.com/updatestatus.php";
        String sendnotif_url = "http://quotidian-twins.000webhostapp.com/sendnotif.php";
        String method = params[0];
        if (method.equals("register")) {
            String uid = params[1];
            String usr = params[2];
            String pwd = params[3];
            String phno = params[4];
            String eml = params[5];
            String priv = params[6];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("usr", "UTF-8") + "=" + URLEncoder.encode(usr, "UTF-8") + "&" +
                        URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8") + "&" +
                        URLEncoder.encode("eml", "UTF-8") + "=" + URLEncoder.encode(eml, "UTF-8") + "&" +
                        URLEncoder.encode("phno", "UTF-8") + "=" + URLEncoder.encode(phno, "UTF-8") + "&" +
                        URLEncoder.encode("priv", "UTF-8") + "=" + URLEncoder.encode(priv, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Registration Success...";


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

        } else if (method.equals("login")) {

            String uid = params[1];
            String pwd = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";

                String line = "";
                int f = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("logintest",line);
                    if (line.length() == 1) {
                        f=1;
                        privSingleton var = privSingleton.getInstance();
                        var.setValue(line);
                        var.setIDValue(uid);
                    } else {
                        response += line;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                privSingleton var = privSingleton.getInstance();
                if (f != 0) {
                    int priv = Integer.parseInt(var.getValue());
                    if (priv < 1) {

                        BackgroundTask backgroundTask = new BackgroundTask(this.ctx);
                        backgroundTask.execute("lecs", var.getIDValue());
                    }
                }
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        } else if (method.equals("lecs")) {

            String uid = params[1];

            try {
                privSingleton var = privSingleton.getInstance();
                URL url = new URL(lec_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> list2 = new ArrayList<String>();

                int i = 0;
                int j = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    if (line.length() != 0) {
                        int index = 0;
                        if(line.length()>=3) {
                            if (line.substring(0, 3).equals("lec")) {
                                // Log.d("indextest5","Inside IF");
                                index = line.indexOf(',');
                                // Log.d("indextest4",String.valueOf(j));
                                //  Log.d("indextest6",line.substring(3, index));
                                list2.add(" ");
                                list2.set(j, line.substring(3, index));
                                j++;
                                //  Log.d("indextest",String.valueOf(index));
                                index++;

                            }
                        } else {
                            if(line.equals("1")){
                            line = "On time";
                        } else if (line.equals("0")){
                            line = "canceled";
                        }
                    }
                        // Log.d("indextest2",line.substring(0,3));
                        list.add(line.substring(index));

                        // Log.d("lecstest9",list.get(i));
                        i++;
                    }
                }
                response = "Success.";
                var.setlist2(list2);

                for (String s : list2
                        ) {
                    Log.d("indextest11", s);
                }

                for (String s : list
                        ) {
                    Log.d("indextest11", s);
                }

                //Log.d("settinglist9", String.valueOf(list.size()));
                var.setlist(list);
                //ArrayList<String> test = var.getlist();
                // Log.d("settinglist2", String.valueOf(test.size()));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        } //////
        else if(method.equals("instlec")) {
            String instid = params[1];

            try {
                URL url = new URL(inst_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode ( "uid", "UTF-8" ) + "=" + URLEncoder.encode ( instid, "UTF-8" );



                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> list2 = new ArrayList<String>();
                int i = 0;

                while ((line = bufferedReader.readLine()) != null){
                    line = line.trim();
                    if(line.length() != 0) {
                        //list.add(line);
                        if(i==5||i==6||i==7)
                        {
                            list2.add(line);
                            Log.d("lecstest",line);
                           // if (i == 8)
                           // {
                           //     i=-1;
                          //  }
                        }else if(i==8){
                            i = -1;
                            if(line.equals("1")){
                                line = "On time";
                                list.add(line);
                            } else if (line.equals("0")){
                                line = "canceled";
                                list.add(line);
                            }
                        }
                        else
                        { list.add(line);}
                        i++;
                    }


                    //     Log.d("lecstest2",list.get(i));
                    //i++;
                }
                response = "Success.";
                privSingleton var= privSingleton.getInstance();
                Log.d("settinglist", String.valueOf(list.size()));
                var.setlist(list);
                var.setlist2(list2);

                //ArrayList<String> test = var.getlist();
                //Log.d("settinglist2", String.valueOf(test.size()));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return(e.toString());
            }

        }

        /////

        else if (method.equals("abs")) {

            String uid = params[1];
            String lec_ID = params[2];
            Log.d("lecidtest", String.valueOf(lec_ID));
            try {
                URL url = new URL(abs_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("lec_ID", "UTF-8") + "=" + URLEncoder.encode(lec_ID, "UTF-8");
                ;

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                ArrayList<String> list = new ArrayList<String>();
                int i = 0;


                while ((line = bufferedReader.readLine()) != null) {
                    //Log.d("abstest",line);
                    line = line.trim();
                    if (line.length() != 0) {
                        if (line.equals("00:00:00")) {
                            line = "-";
                        }
                        list.add(line);
                        Log.d("abstest", line);
                        //Log.d("abstest2",list.get(i));
                    }
                    //  Log.d("abstest2",list.get(i));
                    i++;
                }
                for (String s : list
                        ) {
                    Log.d("abstest3", s);
                }
                response = "Successful absence retrieve.";
                privSingleton var = privSingleton.getInstance();
                //Log.d("settinglist", String.valueOf(list.size()));
                var.setlist(list);
                //ArrayList<String> test = var.getlist();
                //Log.d("settinglist2", String.valueOf(test.size()));
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        }

        //////
        else if (method.equals("ent")) {

            String uid = params[1];
            String lecId = params[2];
//Log.d ( "enttest",uid );
            //          Log.d ( "enttest2",lecId );
            try {
                URL url = new URL(ent_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("lecID", "UTF-8") + "=" + URLEncoder.encode(lecId, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";

                String line = "";
                ArrayList<String> list = new ArrayList<String>();
               // int i = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    if (line.length() != 0) {
                        list.add(line);
                       // Log.d("abstest", line);
                        //Log.d("abstest2",list.get(i));
                    }
                    //  Log.d("abstest2",list.get(i));
                    // i++;
                }
               /* for (String s : list
                        ) {
                    Log.d("abstest3", s);
                }*/
                response = "Successful notifications check.";
                privSingleton var = privSingleton.getInstance();
                //Log.d("settinglist", String.valueOf(list.size()));
                var.setlist(list);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }
        }
        /////
        else if (method.equals("update")) {

            String uid = params[1];
            String pwd = params[2];
            String email = params[3];

            try {
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "Fail.";

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    Log.d("updateLog", line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        } else if (method.equals("sendnotif")) {

            String lec_ID = params[1];
            String text = params[2];
            String uid = params[3];

            try {
                URL url = new URL(sendnotif_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("lec_ID", "UTF-8") + "=" + URLEncoder.encode(lec_ID, "UTF-8") + "&" +
                        URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text, "UTF-8") + "&" +
                        URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "Fail.";

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    Log.d("sendLog", line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        }  else if (method.equals("updateStatus")) {

            String lec_ID = params[1];


            try {
                URL url = new URL(updateStatus_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("lec_ID", "UTF-8") + "=" + URLEncoder.encode(lec_ID, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "Fail.";

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    Log.d("updateLog", line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        }
        else if (method.equals("check")) {

            String uid = params[1];
            String flag = params[2];

            try {
                URL url = new URL(check_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("flag", "UTF-8") + "=" + URLEncoder.encode(flag, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "Fail.";

                String line = "";
                if (flag == "1") {
                    ArrayList<String> list = new ArrayList<String>();
                    privSingleton var = privSingleton.getInstance();

                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.trim();
                        if (line.length() != 0) {
                            list.add(line);
                            response += line;
                            Log.d("updateLog", line);
                        }
                    }

                    var.setlist(list);
                } else {
                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.trim();
                        if (line.length() != 0) {
                            response = line;
                            Log.d("notiftest2",line);
                        }
                    }
                    privSingleton var = privSingleton.getInstance();
                    var.setResponse(response);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return (e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return (e.toString());
            }

        } else if (method.equals ( "logchange" )) {

            String lecid = params[1];
            String letime = params[2];
            String lstime = params[3];

            try {
                URL url = new URL ( log_change );
                HttpURLConnection httpURLConnection = ( HttpURLConnection ) url.openConnection ();
                httpURLConnection.setRequestMethod ( "POST" );
                httpURLConnection.setDoOutput ( true );
                httpURLConnection.setDoInput ( true );
                OutputStream outputStream = httpURLConnection.getOutputStream ();
                BufferedWriter bufferedWriter = new BufferedWriter ( new OutputStreamWriter ( outputStream, "UTF-8" ) );

                String data = URLEncoder.encode ( "lec_ID", "UTF-8" ) + "=" + URLEncoder.encode ( lecid, "UTF-8" ) + "&" +
                        URLEncoder.encode ( "log_start_time", "UTF-8" ) + "=" + URLEncoder.encode ( letime, "UTF-8" ) + "&" +
                        URLEncoder.encode ( "log_end_time", "UTF-8" ) + "=" + URLEncoder.encode ( lstime, "UTF-8" );

                bufferedWriter.write ( data );
                bufferedWriter.flush ();
                bufferedWriter.close ();
                outputStream.close ();
                InputStream inputStream = httpURLConnection.getInputStream ();
                BufferedReader bufferedReader = new BufferedReader ( new InputStreamReader ( inputStream, "iso-8859-1" ) );
                String response = "Fail.";

                String line = "";
                while ((line = bufferedReader.readLine ()) != null) {
                    response += line;
                    Log.d ( "updateLog5555", line );
                }
                bufferedReader.close ();
                inputStream.close ();
                httpURLConnection.disconnect ();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace ();
                return (e.toString ());
            } catch (IOException e) {
                e.printStackTrace ();
                return (e.toString ());
            }

        }
        return "empty return";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        //privSingleton var= privSingleton.getInstance();
        if (result.equals("Registration Success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        } else if (result.equals("Success.") && !(this instanceof BackgroundTask)) {
            alertDialog.setMessage(result);
            alertDialog.show();
        } else if (result.contains("Login Success")) {
            Intent i = new Intent(ctx, MainPageActivity.class);
            ctx.startActivity(i);
        } else if (result.contains("Login Failed.")) {
            Toast.makeText(ctx, "Invalid credentials", Toast.LENGTH_SHORT).show();
        } else if (result.contains ( "log start time updated." )) {
            // BaseActivity.logout();
            //Intent i = new Intent ( ctx, MainPageActivity.class );
            //ctx.startActivity ( i );
        } else if (result.contains ( "log end time updated." )){
            //Intent i = new Intent ( ctx, TimeActivity.class );
            // ctx.startActivity ( i );
            // Toast.makeText ( ctx, "Success!", Toast.LENGTH_LONG ).show ();
        } else if (result.contains ( "Changing log time failed!." )){
            // alertDialog.setMessage(result);
            //  alertDialog.show();
            Toast.makeText ( ctx, "failed!", Toast.LENGTH_LONG ).show ();
        } else if (result.contains("updated.") || result.contains("Both values are")) {
            // alertDialog.setMessage(result);
            //  alertDialog.show();
        } else if (result.contains("NOTALEC")) {
            Toast.makeText(ctx, "You are not an instructor for this lecture.", Toast.LENGTH_SHORT).show();
        } else if (result.contains("sent.")) {
            Toast.makeText(ctx, "Notifications sent.", Toast.LENGTH_SHORT).show();
        }
    }
}
