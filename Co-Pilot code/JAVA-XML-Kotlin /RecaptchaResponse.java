package com.example.khalid.CoPilot;

import com.google.gson.annotations.SerializedName;

public class RecaptchaResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String Message;

    public String getStatus(){
        return status;
    }

    public String getMessage() {
        return Message;
    }
}
