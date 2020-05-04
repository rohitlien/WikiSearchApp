package com.rohit.wikisearchapp.retrofit;

import com.rohit.wikisearchapp.beans.WikiSearchDataCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitServices {

    @GET
    Call<WikiSearchDataCollection> search(
            @Url String url
    );


}
