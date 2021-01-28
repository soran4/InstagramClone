package com.soran.myinstagramclone.WebService;

import com.soran.myinstagramclone.model.PixabayPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("?key=19303089-66edf185f54fe53c5688f5df1&q=computer+android&image_type=photo&pretty=true")
    Call<PixabayPosts> getAllPosts();

    @GET("?key=19303089-66edf185f54fe53c5688f5df1&image_type=photo&pretty=true")
    Call<PixabayPosts> getPostsBySearch(@Query("q") String keyWord);
}
