package com.example.airy_receipt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("bins/171v5n")
    Call<DataJson> getPosts();
}
