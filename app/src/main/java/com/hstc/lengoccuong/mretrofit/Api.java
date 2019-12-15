package com.hstc.lengoccuong.mretrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    String BASE_URL = "https://www.simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<ArrayList<Hero>> getHeroes();
}
