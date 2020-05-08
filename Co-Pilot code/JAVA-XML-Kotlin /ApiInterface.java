package com.example.khalid.CoPilot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("recaptcha_validation.php")
    Call<RecaptchaResponse> validateRecaptcha(@Query("response_key") String response_key, @Query("feedback") String feedback);


}
