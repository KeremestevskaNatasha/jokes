package com.example.chucknorrisjokes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chucknorrisjokes.model.GetAllJokes;
import com.example.chucknorrisjokes.model.Joke;
import com.example.chucknorrisjokes.network.JokesRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView rvJokes;
    private TextView tvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        rvJokes = findViewById(R.id.rvJokes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL,false);
        rvJokes.setLayoutManager(linearLayoutManager);

//        rvJokes.setLayoutManager(new LinearLayoutManager(this));
        JokesRetrofitClient.getJokesApi().getAllJokes().enqueue(new Callback<GetAllJokes>() {
            @Override
            public void onResponse(Call<GetAllJokes> call, Response<GetAllJokes> response) {

                progressBar.setVisibility(View.GONE);
                tvError.setVisibility(View.GONE);

                List<Joke> value = response.body().getValue();
               JokesAdapter adapter = new JokesAdapter(MainActivity.this, value);

               rvJokes.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<GetAllJokes> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                tvError.setVisibility(View.GONE);

            }
        });

    }
}
