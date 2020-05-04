package com.rohit.wikisearchapp.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rohit.wikisearchapp.BuildConfig;
import com.rohit.wikisearchapp.utils.AppConstants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory() {
    }

    public static Retrofit getRetrofit() {

        if(retrofit == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            client.connectTimeout(60, TimeUnit.SECONDS);
            client.readTimeout(60, TimeUnit.SECONDS);
            client.writeTimeout(60, TimeUnit.SECONDS);
            client.addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request original = chain.request();
                    String version = BuildConfig.VERSION_NAME;
                    Request request = original.newBuilder()
                            .header("os", "android")
                            .header("appversion", version)
                            .header("appname", "WikiSearch")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            retrofit = new Retrofit.Builder().client(client.build()).baseUrl(AppConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
