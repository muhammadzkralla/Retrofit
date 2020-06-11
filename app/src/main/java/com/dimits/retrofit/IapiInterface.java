package com.dimits.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IapiInterface {

    @GET("posts/2")
    public Call<Post> getPost();
}
