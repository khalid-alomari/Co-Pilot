package com.example.khalid.CoPilot;

import java.util.ArrayList;

public class privSingleton {

    private static privSingleton instance;

    public static privSingleton getInstance() {
        if (instance == null)
            instance = new privSingleton();
        return instance;
    }

    private privSingleton() {
    }
    private int loginCounter=0;
    private String response;
    private String val;
    private String idval;
    private String pwdHash = "";
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> list3 = new ArrayList<String> ();
    private String id;
    private int id2;


    public int getLoginCounter() {
        return loginCounter;
    }
    public void setLoginCounter(int value) {
        this.loginCounter = value;
    }

    public String getResponse() {
        return response;
    }
    public void setResponse(String value) {
        this.response = value;
    }

    public String getPwdHash() {
        return pwdHash;
    }
    public void setPwdHash(String value) {
        this.pwdHash = value;
    }

    public String getValue() {
        return val;
    }
    public void setValue(String value) {
        this.val = value;
    }

    public void setIDValue (String value) { this.idval = value;}
    public String getIDValue() {
        return idval;
    }

    public void setlist (ArrayList<String> value) { this.list = value;}
    public ArrayList<String> getlist() {
        return list;
    }

    public void setlist2 (ArrayList<String> value) { this.list2 = value;}
    public ArrayList<String> getlist2() {
        return list2;
    }

    public String getID() {
        return id;
    }
    public void setID(String value) {
        this.id = value;
    }

    public void setlist3 (ArrayList<String> value) { this.list3 = value;}
    public ArrayList<String> getlist3() {
        return list3;
    }

    public int getPostion() {
        return id2;
    }
    public void setPostion(int value) {
        this.id2 = value;
    }
}


