package com.sadikahmetozdemir.caseforzz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("doctors.json")
    Call<Example> getDoctorsJson();

}
