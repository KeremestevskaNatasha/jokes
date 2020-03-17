package com.example.chucknorrisjokes.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesRetrofitClient {

    public static final String BASE_URL = "https://api.icndb.com/";

    private static JokesApi jokeApi;

    public static JokesApi getJokesApi() {
        if (jokeApi == null) {
            createJokesApi();
        }
        return jokeApi;
    }

    private static void createJokesApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jokeApi = retrofit.create(JokesApi.class);
    }

}
